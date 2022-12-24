package today.parkh.imagineissues.image.karlo;

import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import today.parkh.imagineissues.image.karlo.dto.request.MakeImagePrompt;
import today.parkh.imagineissues.image.karlo.dto.request.MakeImageRequest;
import today.parkh.imagineissues.image.karlo.dto.response.MakeImageResponse;

@SpringBootTest
class KarloServiceTest {

    @Autowired
    KarloService karloService;

    @Value("${kakao.authorization}")
    private String kakaoAuthorization;

    @DisplayName("Karlo - 이미지 생성")
//    @Test
    public void makeImage() {
        String url = "https://api.kakaobrain.com/v1/inference/karlo/t2i";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", kakaoAuthorization);

        MakeImagePrompt prompt = new MakeImagePrompt("A lake, alpine, vivid", 1);
        MakeImageRequest body = new MakeImageRequest(prompt);

        HttpEntity<MakeImageRequest> request = new HttpEntity<>(body, headers);

        ResponseEntity<MakeImageResponse> response = new RestTemplate().postForEntity(url, request, MakeImageResponse.class);
        MakeImageResponse responseBody = response.getBody();
    }
}