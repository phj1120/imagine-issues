package today.parkh.imagineissues;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import today.parkh.imagineissues.image.ImageService;
import today.parkh.imagineissues.image.KarloService;
import today.parkh.imagineissues.image.response.KarloImage;
import today.parkh.imagineissues.post.InstagramService;
import today.parkh.imagineissues.post.dto.Post;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TotalService {
    private final InstagramService instagramService;
    private final KarloService karloService;
    private final ImageService imageService;

    public Post posting() throws IOException {
        String imageBaseUrl = "localhost:8080/api/image";

        String keyword = "캠핑 책스타그램 국내여행 타투 가울 운동 폰케이스 반려동물 셀카 패션스타그램 운동하는 여자부산 주얼리 부동산 감성 패션 요가 비건 필라테스 헬스타그램";
        KarloImage karloImage = karloService.makeImage(keyword);
        String imageEncodedBase64 = karloImage.getImage();

        String fileName = UUID.randomUUID().toString().substring(0, 20);
        imageService.saveBase64toImage(fileName, imageEncodedBase64);
        Post post = instagramService.publishPost(imageBaseUrl + "/" + fileName, keyword);

        return post;
    }

}
