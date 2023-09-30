package today.parkh.ainimai.post.instagram;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import today.parkh.ainimai.comment.dto.Prompt;
import today.parkh.ainimai.post.dto.Post;
import today.parkh.ainimai.post.service.instagram.InstagramService;

@SpringBootTest
class InstagramServiceTest {
    @Autowired
    InstagramService instagramService;

    // 진짜 포스팅 되기 때문에 생각하고 할 것
    @Test
    public void posting() {
        Post post = instagramService.publishSinglePost(new Prompt("a dog", "winter", "house", "sleep"));
        Assertions.assertThat(post).isNotNull();
    }
}