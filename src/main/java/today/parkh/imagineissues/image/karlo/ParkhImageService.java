package today.parkh.imagineissues.image.karlo;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ParkhImageService {
    public static final String BASE_URL = "http://www.parkh.today:11201/";

    public String base64ImageToUrl(String encodedImage) {
        String base64ImageToUrl = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .path("/image")
                .build().toUriString();

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("encodedImage", encodedImage);
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, null);

        ResponseEntity<String> response = new RestTemplate().postForEntity(base64ImageToUrl, request, String.class);
        String storedName = response.getBody();

        String readImageUrl = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .path("/image")
                .path("/" + storedName)
                .build().toUriString();

        return readImageUrl;
    }
}
