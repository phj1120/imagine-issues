package today.parkh.ainimai.comment.instagram;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import today.parkh.ainimai.comment.dto.response.IGComment;
import today.parkh.ainimai.comment.dto.response.IGMedia;

import java.util.List;

@SpringBootTest
class InstagramIGCommentServiceTest {

    @Autowired
    InstagramCommentService instagramCommentService;

    @Test
    public void getIGMedias() {
        List<IGMedia> igMedias = instagramCommentService.getIGMedias();

        Assertions.assertThat(igMedias).isNotEmpty();
    }


    @Test
    public void getRecentIGMedia() {
        IGMedia recentIGMedia = instagramCommentService.getRecentIGMedia();

        Assertions.assertThat(recentIGMedia).isNotNull();
    }

    @Test
    public void getComments() {
        IGMedia recentIGMedia = instagramCommentService.getRecentIGMedia();

        List<IGComment> comments = instagramCommentService.getSimpleComments(recentIGMedia.getId());
        Assertions.assertThat(comments).isNotEmpty();
    }

    @Test
    public void getComment() {
        IGMedia recentIGMedia = instagramCommentService.getRecentIGMedia();

        List<IGComment> simpleComments = instagramCommentService.getSimpleComments(recentIGMedia.getId());
        for (IGComment simpleComment : simpleComments) {
            IGComment comment = instagramCommentService.getComment(simpleComment.getId());
            Assertions.assertThat(comment).isNotNull();
        }
    }
}