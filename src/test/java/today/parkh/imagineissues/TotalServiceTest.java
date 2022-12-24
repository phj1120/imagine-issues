package today.parkh.imagineissues;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import today.parkh.imagineissues.post.dto.Post;

import java.io.IOException;

@SpringBootTest
class TotalServiceTest {

    @Autowired
    TotalService totalService;

    @Test
    public void publish() throws IOException {
        Post post = totalService.posting();
        System.out.println(post);
    }

}