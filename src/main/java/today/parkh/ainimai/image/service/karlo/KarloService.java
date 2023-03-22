package today.parkh.ainimai.image.service.karlo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import today.parkh.ainimai.image.service.ImageService;
import today.parkh.ainimai.image.service.karlo.dto.request.MakeImagePrompt;
import today.parkh.ainimai.image.service.karlo.dto.request.MakeImageRequest;
import today.parkh.ainimai.image.service.karlo.dto.response.KarloImage;
import today.parkh.ainimai.image.service.karlo.dto.response.MakeImageResponse;
import today.parkh.ainimai.image.saver.ImageSaver;

@Slf4j
@Service
@RequiredArgsConstructor
public class KarloService implements ImageService {
    public static final String MAKE_IMAGE_URL = "https://api.kakaobrain.com/v1/inference/karlo/t2i";

    private final ImageSaver imageSaver;

    @Value("${kakao.authorization}")
    private String kakaoAuthorization;

    public String makeImageUrl(String text) {
        KarloImage karloImage = makeImage(text);
        String imageUrl = imageSaver.base64ImageToUrl(karloImage.getImage());

        return imageUrl;
    }

    public KarloImage makeImage(String text) {
        // 헤더 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", kakaoAuthorization);

        // 리퀘스트 생성
        MakeImagePrompt prompt = new MakeImagePrompt(text, 1);
        MakeImageRequest body = new MakeImageRequest(prompt);
        HttpEntity<MakeImageRequest> request = new HttpEntity<>(body, headers);

        // 요청 전송
        ResponseEntity<MakeImageResponse> response = new RestTemplate().postForEntity(MAKE_IMAGE_URL, request, MakeImageResponse.class);

        // 요청 결과 처리
        MakeImageResponse responseBody = response.getBody();
        KarloImage karloImage = responseBody.getImages().get(0);

        return karloImage;
    }
}
