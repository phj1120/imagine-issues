package today.parkh.ainimai.common.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import today.parkh.ainimai.comment.dto.Prompt;
import today.parkh.ainimai.comment.sevice.instagram.InstagramCommentService;
import today.parkh.ainimai.post.service.instagram.InstagramService;

import java.time.LocalDate;


@Component
@RequiredArgsConstructor
@Slf4j
public class ApplicationScheduler {
    private final InstagramCommentService instagramCommentService;
    private final InstagramService instagramService;

    @Scheduled(cron = "0 0 19 * * * ")
    public void publish() {
        log.info("[{}]", LocalDate.now());
        log.info("[publish start]");

        Prompt prompt = instagramCommentService.generatePrompt();
        log.info("[prompt] : {}", prompt);

        instagramService.publishSinglePost(prompt);
        log.info("[publish success]");
    }
}
