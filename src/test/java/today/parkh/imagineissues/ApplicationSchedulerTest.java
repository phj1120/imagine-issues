package today.parkh.imagineissues;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import today.parkh.imagineissues.post.dto.vo.Post;

@SpringBootTest
class ApplicationSchedulerTest {

    @Autowired
    ApplicationScheduler applicationScheduler;

    @Test
    public void publish() {
        String keyword = "Cute, dog, play, happy, day, warm, painting drawing style";
        Post post = applicationScheduler.posting(keyword);

        System.out.println(post);
    }
}