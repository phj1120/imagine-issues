package today.parkh.ainimai;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import today.parkh.ainimai.comment.Prompt;
import today.parkh.ainimai.comment.instagram.InstagramCommentService;
import today.parkh.ainimai.image.ImageService;
import today.parkh.ainimai.post.dto.vo.Post;
import today.parkh.ainimai.post.instagram.InstagramService;

import java.time.LocalDate;


@Component
@RequiredArgsConstructor
@Slf4j
public class ApplicationScheduler {
    private final InstagramCommentService instagramCommentService;
    private final InstagramService instagramService;
    private final ImageService imageService;

    @Scheduled(cron = "0 0 19 * * * ")
    public void publish() {
        log.info("[{}]", LocalDate.now());
        log.info("[publish start]");

        Prompt prompt = instagramCommentService.generatePrompt();
        log.info("[prompt] : {}", prompt);

        String imageUrl = imageService.makeImageUrl(prompt.getString());
        log.info("[image url] : {}", imageUrl);

        instagramService.publishSinglePost(imageUrl, prompt.getString());
        log.info("[publish success]");
    }
}
