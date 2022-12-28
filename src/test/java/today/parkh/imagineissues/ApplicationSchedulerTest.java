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
        String keyword = "Cats, winter, ski resorts, eat apples, by pixar art";
//        String keyword = "hyerophant, god light, cinematic look, octane render, under water, --wallpaper";
        Post post = applicationScheduler.posting(keyword);

        System.out.println(post);
    }
}