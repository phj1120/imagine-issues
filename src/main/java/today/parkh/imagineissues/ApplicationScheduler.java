package today.parkh.imagineissues;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import today.parkh.imagineissues.image.ImageService;
import today.parkh.imagineissues.post.dto.vo.Post;
import today.parkh.imagineissues.post.instagram.InstagramService;

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
