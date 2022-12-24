package today.parkh.imagineissues.post.instagram;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import today.parkh.imagineissues.post.PostService;
import today.parkh.imagineissues.post.dto.vo.Post;
import today.parkh.imagineissues.post.instagram.dto.response.CheckPublishLimitResponse;
import today.parkh.imagineissues.post.instagram.dto.response.MakeContainerResponse;
import today.parkh.imagineissues.post.instagram.dto.response.PostResponse;
import today.parkh.imagineissues.post.instagram.dto.response.PublishingResponse;

@Service
public class InstagramService implements PostService {
    private static final int MAX_PUBLISH_COUNT = 25;

    private String instagramBaseUrl = "https://graph.facebook.com";
    private String apiVersion = "v15.0";

    @Value("${ig.accessToken}")
    private String igAccessToken;

    @Value("${ig.userId}")
    private String igUserId;

    public Post publishSinglePost(String imageUrl, String content) {
        if (getPublishCount() > MAX_PUBLISH_COUNT) {
            throw new IllegalArgumentException("한도 초과");
        }
        String igContainerId = makeContainer(imageUrl, content);
        publishContainer(igContainerId);

        return new Post(imageUrl, content);
    }

    public int getPublishCount() {
        String checkUrl = instagramBaseUrl + "/" + apiVersion + "/" + igUserId + "/content_publishing_limit?access_token=" + igAccessToken;
        ResponseEntity<CheckPublishLimitResponse> response = new RestTemplate().getForEntity(checkUrl, CheckPublishLimitResponse.class);

        return response.getBody().getData().get(0).getQuota_usage();
    }

    public String makeContainer(String imageUrl, String content) {
        String postingUrl = instagramBaseUrl + "/" + apiVersion + "/" + igUserId + "/media?access_token=" + igAccessToken + "&image_url=" + imageUrl + "&caption=" + content;
        ResponseEntity<MakeContainerResponse> response = new RestTemplate().postForEntity(postingUrl, null, MakeContainerResponse.class);
        String igContainerId = response.getBody().getId();

        return igContainerId;
    }

    public String publishContainer(String igContainerId) {
        String publishingUrl = instagramBaseUrl + "/" + apiVersion + "/" + igUserId + "/media_publish?access_token=" + igAccessToken + "&creation_id=" + igContainerId;
        ResponseEntity<PublishingResponse> response = new RestTemplate().postForEntity(publishingUrl, null, PublishingResponse.class);
        String igMediaId = response.getBody().getId();

        return igMediaId;
    }

    public PostResponse readPost(String igMediaId) {
        String getMediaId = instagramBaseUrl + "/" + apiVersion + "/" + igMediaId + "?access_token=" + igAccessToken;
        ResponseEntity<PostResponse> response = new RestTemplate().getForEntity(getMediaId, PostResponse.class);

        return response.getBody();
    }
}
