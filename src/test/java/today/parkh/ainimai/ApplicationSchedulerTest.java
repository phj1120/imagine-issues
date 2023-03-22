package today.parkh.ainimai;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import today.parkh.ainimai.comment.instagram.InstagramCommentService;
import today.parkh.ainimai.image.service.ImageService;
import today.parkh.ainimai.post.instagram.InstagramService;

@SpringBootTest
class ApplicationSchedulerTest {

    @Autowired
    ApplicationScheduler applicationScheduler;

    @Autowired
    InstagramCommentService instagramCommentService;

    @Autowired
    InstagramService instagramService;

    @Autowired
    ImageService imageService;

    @Test
    public void publish() {
        applicationScheduler.publish();
    }
}