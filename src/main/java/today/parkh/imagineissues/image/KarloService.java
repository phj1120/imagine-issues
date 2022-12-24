package today.parkh.imagineissues.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import today.parkh.imagineissues.image.request.MakeImagePrompt;
import today.parkh.imagineissues.image.request.MakeImageRequest;
import today.parkh.imagineissues.image.response.KarloImage;
import today.parkh.imagineissues.image.response.MakeImageResponse;

@Service
public class KarloService {

    @Autowired
    ParkhImageService parkhImageService;

    @Value("${kakao.authorization}")
    private String kakaoAuthorization;

    public String makeImageUrl(String text) {
        KarloImage karloImage = makeImage(text);
        String imageUrl = parkhImageService.base64ImageToUrl(karloImage.getImage());

        return imageUrl;
    }

    private KarloImage makeImage(String text) {
        String url = "https://api.kakaobrain.com/v1/inference/karlo/t2i";

        // 헤더 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", kakaoAuthorization);

        // 리퀘스트 생성
        MakeImagePrompt prompt = new MakeImagePrompt(text, 1);
        MakeImageRequest body = new MakeImageRequest(prompt);
        HttpEntity<MakeImageRequest> request = new HttpEntity<>(body, headers);

        // 요청 전송
        ResponseEntity<MakeImageResponse> response = new RestTemplate().postForEntity(url, request, MakeImageResponse.class);

        // 요청 결과 처리
        MakeImageResponse responseBody = response.getBody();
        KarloImage karloImage = responseBody.getImages().get(0);

        return karloImage;
    }


}
