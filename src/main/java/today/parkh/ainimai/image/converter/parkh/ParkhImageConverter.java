package today.parkh.ainimai.image.converter.parkh;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import today.parkh.ainimai.image.converter.ImageConverter;
import today.parkh.ainimai.post.dto.ImageData;
import today.parkh.ainimai.post.dto.ImageUrl;

@Service
public class ParkhImageConverter implements ImageConverter {
    public static final String BASE_URL = "http://www.parkh.today:11201/";

    @Override
    public ImageUrl dataToUrl(ImageData originalImage) {
        ResponseEntity<String> response = requestSaveImage(originalImage);

        // 원하는 정보 가공
        String storedName = response.getBody();
        String readImageUrl = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .path("/image")
                .path("/" + storedName)
                .build().toUriString();
        ImageUrl newImage = new ImageUrl(readImageUrl);

        return newImage;
    }

    private static ResponseEntity<String> requestSaveImage(ImageData originalImage) {
        // 이미지 서버에 보낼 요청 생성
        String imageConvertUrl = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .path("/image")
                .build().toUriString();
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        String imageData = originalImage.getImage();
        body.add("encodedImage", imageData);
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, null);

        // 이미지 저장 요청
        ResponseEntity<String> response = new RestTemplate().postForEntity(imageConvertUrl, request, String.class);

        return response;
    }

    @Override
    public ImageData urlToData(ImageUrl imageUrl) {
        return null;
    }

}
