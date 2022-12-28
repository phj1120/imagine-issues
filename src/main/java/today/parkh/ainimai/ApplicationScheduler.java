package today.parkh.ainimai;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import today.parkh.ainimai.image.ImageService;
import today.parkh.ainimai.post.dto.vo.Post;
import today.parkh.ainimai.post.instagram.InstagramService;

@RequiredArgsConstructor
@Component
public class ApplicationScheduler {
    private final InstagramService instagramService;
    private final ImageService imageService;

    @Scheduled(cron = "0 0 19 * * * ")
    public Post posting(String keyword) {
        String imageUrl = imageService.makeImageUrl(keyword);
        Post post = instagramService.publishSinglePost(imageUrl, keyword);

        return post;
    }
}
