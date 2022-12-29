package today.parkh.ainimai.comment.instagram;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InstagramCommentServiceTest {

    @Autowired
    InstagramCommentService instagramCommentService;

    @Test
    public void getPostList() {
        List<String> postList = instagramCommentService.getPostList();

        System.out.println(postList);
    }

}