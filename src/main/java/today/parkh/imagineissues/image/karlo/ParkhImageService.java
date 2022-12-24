package today.parkh.imagineissues.image.karlo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class ParkhImageService {

    @Value("${parkh.url.base}")
    private String parkhBaseUrl;

    public String base64ImageToUrl(String encodedImage) {


        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("encodedImage", encodedImage);

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, null);

        ResponseEntity<String> response = new RestTemplate().postForEntity(parkhBaseUrl, request, String.class);
        String imageUrl = parkhBaseUrl + "/" + response.getBody();

        return imageUrl;
    }
}
