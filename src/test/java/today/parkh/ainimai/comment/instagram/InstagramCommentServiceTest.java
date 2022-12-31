package today.parkh.ainimai.comment.instagram;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class InstagramCommentServiceTest {

    @Autowired
    InstagramCommentService instagramCommentService;

    @Test
    public void getPostList() {
        List<String> posts = instagramCommentService.getIGMediaList();

        Assertions.assertThat(posts).isNotEmpty();
    }


    @Test
    public void getRecentPostId() {
        String recentPostId = instagramCommentService.getRecentIGMediaId();

        Assertions.assertThat(recentPostId).isNotEmpty();
    }
}