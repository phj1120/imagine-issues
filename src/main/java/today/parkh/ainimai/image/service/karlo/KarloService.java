package today.parkh.ainimai.image.service.karlo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import today.parkh.ainimai.comment.dto.Prompt;
import today.parkh.ainimai.image.service.ImageService;
import today.parkh.ainimai.image.service.karlo.dto.request.MakeImageRequest;
import today.parkh.ainimai.image.service.karlo.dto.response.KarloImage;
import today.parkh.ainimai.image.service.karlo.dto.response.MakeImageResponse;
import today.parkh.ainimai.image.converter.ImageConverter;
import today.parkh.ainimai.post.dto.Image;
import today.parkh.ainimai.post.dto.ImageData;

@Slf4j
@Service
@RequiredArgsConstructor
public class KarloService implements ImageService {
    public static final String MAKE_IMAGE_URL = "https://api.kakaobrain.com/v2/inference/karlo/t2i";
    @Value("${kakao.authorization}")
    private String kakaoAuthorization;
    private final ImageConverter imageSaver;

    @Override
    public Image makeImage(Prompt prompt) {
        // prompt 기반으로 이미지 생성
        String promptText = prompt.getPromptText();
        KarloImage karloImage = requestMakeImage(promptText);
        ImageData imageData = new ImageData(karloImage.getImage());

        // 이미지 서버에 저장해 URL 주소로 변경
        Image imageUrl = imageSaver.dataToUrl(imageData);

        return imageUrl;
    }

    private KarloImage requestMakeImage(String text) {
        // 헤더 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", kakaoAuthorization);

        // 리퀘스트 생성
        MakeImageRequest body = MakeImageRequest.of(text);
        HttpEntity<MakeImageRequest> request = new HttpEntity<>(body, headers);

        // 요청 전송
        ResponseEntity<MakeImageResponse> response = new RestTemplate().postForEntity(MAKE_IMAGE_URL, request, MakeImageResponse.class);

        // 요청 결과 처리
        MakeImageResponse responseBody = response.getBody();
        KarloImage karloImage = responseBody.getImages().get(0);

        return karloImage;
    }
}
