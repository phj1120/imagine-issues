package today.parkh.imagineissues.post.instagram;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import today.parkh.imagineissues.post.dto.vo.Post;

@SpringBootTest
class InstagramServiceTest {
    @Autowired
    InstagramService instagramService;

    // 진짜 포스팅 되기 때문에 생각하고 할 것
//    @Test
    public void posting() {
        String imageUrl = "https://img.freepik.com/free-vector/realistic-galaxy-background_52683-12122.jpg";
        String content = "title";

        Post post = instagramService.publishSinglePost(imageUrl, content);
        Assertions.assertThat(post).isNotNull();
    }
}