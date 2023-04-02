package today.parkh.ainimai.post.service.instagram;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import today.parkh.ainimai.comment.dto.Prompt;
import today.parkh.ainimai.image.service.ImageService;
import today.parkh.ainimai.post.service.PostService;
import today.parkh.ainimai.post.dto.Image;
import today.parkh.ainimai.post.dto.Post;
import today.parkh.ainimai.post.service.instagram.dto.response.CheckPublishLimitResponse;
import today.parkh.ainimai.post.service.instagram.dto.response.MakeContainerResponse;
import today.parkh.ainimai.post.service.instagram.dto.response.PostResponse;
import today.parkh.ainimai.post.service.instagram.dto.response.PublishingResponse;

import java.net.URI;

@Slf4j
@Service
@RequiredArgsConstructor
public class InstagramService implements PostService {
    public static final int MAX_PUBLISH_COUNT = 25;
    public static final String INSTAGRAM_BASE_URL = "https://graph.facebook.com/v15.0";

    private final ImageService imageService;


    @Value("${instagram.accessToken}")
    private String igAccessToken;

    @Value("${instagram.id.user}")
    private String igUserId;

    @Override
    public Post publishSinglePost(Prompt prompt) {
        // 댓글 기반으로 이미지 생성
        Image image = imageService.makeImage(prompt);
        String imageUrl = image.getImage();
        log.info("[image url] : {}", imageUrl);

        // 이미지 게시
        if (getUsageQuotaCount() > MAX_PUBLISH_COUNT) {
            throw new IllegalArgumentException("한도 초과");
        }
        String igContainerId = makeContainer(imageUrl, prompt);
        publishContainer(igContainerId);

        return new Post(image, prompt.getContent());
    }

    private int getUsageQuotaCount() {
        String checkPublishCountUri = UriComponentsBuilder
                .fromHttpUrl(INSTAGRAM_BASE_URL)
                .path("/" + igUserId)
                .path("/content_publishing_limit")
                .queryParam("access_token", igAccessToken)
                .build().toUriString();

        ResponseEntity<CheckPublishLimitResponse> response = new RestTemplate().getForEntity(checkPublishCountUri, CheckPublishLimitResponse.class);

        return response.getBody().getData().get(0).getQuota_usage();
    }

    public String makeContainer(String imageUrl, Prompt prompt) {
        URI makeContainerUri = UriComponentsBuilder
                .fromHttpUrl(INSTAGRAM_BASE_URL)
                .path("/" + igUserId)
                .path("/media")
                .queryParam("access_token", igAccessToken)
                .queryParam("image_url", imageUrl)
                .queryParam("caption", prompt.getContent())
                .build().toUri();
        ResponseEntity<MakeContainerResponse> response = new RestTemplate().postForEntity(makeContainerUri, null, MakeContainerResponse.class);
        String igContainerId = response.getBody().getId();

        return igContainerId;
    }

    public String publishContainer(String igContainerId) {
        String publishContainerUri = UriComponentsBuilder.fromHttpUrl(INSTAGRAM_BASE_URL)
                .path("/" + igUserId)
                .path("/media_publish")
                .queryParam("access_token", igAccessToken)
                .queryParam("creation_id", igContainerId)
                .build().toUriString();

        ResponseEntity<PublishingResponse> response = new RestTemplate().postForEntity(publishContainerUri, null, PublishingResponse.class);
        String igMediaId = response.getBody().getId();

        return igMediaId;
    }

    public PostResponse readPost(String igMediaId) {
        String readPostUri = UriComponentsBuilder.fromHttpUrl(INSTAGRAM_BASE_URL)
                .path("/")
                .path(igMediaId)
                .queryParam("access_token", igAccessToken)
                .build().toUriString();

        ResponseEntity<PostResponse> response = new RestTemplate().getForEntity(readPostUri, PostResponse.class);

        return response.getBody();
    }
}
