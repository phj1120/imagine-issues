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
        String keyword = "캠핑 책스타그램 국내여행 타투 가울 운동 폰케이스 반려동물 셀카 패션스타그램 운동하는 부산 주얼리 부동산 감성 패션 요가 비건 필라테스 헬스타그램";

        Post post = totalService.posting(keyword);
        System.out.println(post);
    }

}