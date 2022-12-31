package today.parkh.ainimai.comment.instagram;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import today.parkh.ainimai.comment.dto.response.IGComment;

import java.util.List;

@SpringBootTest
class InstagramIGCommentServiceTest {

    @Autowired
    InstagramCommentService instagramCommentService;

    @Test
    public void getIGMediaList() {
        List<String> igMedias = instagramCommentService.getIGMediaList();

        Assertions.assertThat(igMedias).isNotEmpty();
    }


    @Test
    public void getRecentIGMediaId() {
        String recentIGMediaId = instagramCommentService.getRecentIGMediaId();

        Assertions.assertThat(recentIGMediaId).isNotEmpty();
    }

    @Test
    public void getCommentList() {
        String recentIGMediaId = instagramCommentService.getRecentIGMediaId();

        List<IGComment> commentList = instagramCommentService.getSimpleComments(recentIGMediaId);
        Assertions.assertThat(commentList).isNotEmpty();
    }

    @Test
    public void getComment() {
        String recentIGMediaId = instagramCommentService.getRecentIGMediaId();

        List<IGComment> comments = instagramCommentService.getSimpleComments(recentIGMediaId);
        for (IGComment comment : comments) {
            IGComment commentDetail = instagramCommentService.getComment(comment.getId());
            System.out.println(commentDetail);
        }
    }
}