package today.parkh.ainimai.image.karlo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;
import today.parkh.ainimai.image.service.karlo.KarloService;
import today.parkh.ainimai.image.service.karlo.dto.request.MakeImageRequest;
import today.parkh.ainimai.image.service.karlo.dto.response.MakeImageResponse;

@SpringBootTest
class KarloServiceTest {

    @Autowired
    KarloService karloService;

    @Value("${kakao.authorization}")
    private String kakaoAuthorization;

    @DisplayName("Karlo - 이미지 생성")
    @Test
    public void makeImage() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", kakaoAuthorization);

        MakeImageRequest body = MakeImageRequest.of("A lake, alpine, vivid");

        HttpEntity<MakeImageRequest> request = new HttpEntity<>(body, headers);

        MakeImageResponse response = new RestTemplate().postForEntity(KarloService.MAKE_IMAGE_URL, request, MakeImageResponse.class).getBody();

        Assertions.assertThat(response.getImages()).isNotEmpty();
    }
}