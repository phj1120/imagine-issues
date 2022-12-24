package today.parkh.imagineissues;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import today.parkh.imagineissues.post.InstagramService;
import today.parkh.imagineissues.post.dto.Post;

@SpringBootTest
public class PostingTests {
    @Autowired
    InstagramService instagramService;

    // 진짜 포스팅 되기 때문에 생각하고 할 것
    @Test
    public void posting() {
//        String imageUrl = "https://img.freepik.com/free-vector/realistic-galaxy-background_52683-12122.jpg";
        String imageUrl = "http://www.parkh.today:11201/image/826c9b46-1e77-4991-abcb-bda3edee9bfb.png";
        String content = "title";

        Post post = instagramService.publishPost(imageUrl, content);
        Assertions.assertThat(post).isNotNull();
    }
}
