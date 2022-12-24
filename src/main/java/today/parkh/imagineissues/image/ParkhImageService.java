package today.parkh.imagineissues.image;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class ParkhImageService {

    public String base64ImageToUrl(String encodedImage) {
        String baseUrl = "http://www.parkh.today:11201/image";

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("encodedImage", encodedImage);

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, null);

        ResponseEntity<String> response = new RestTemplate().postForEntity(baseUrl, request, String.class);
        String imageUrl = baseUrl + "/" + response.getBody();

        return imageUrl;
    }
}
