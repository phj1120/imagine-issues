package today.parkh.ainimai.image.saver.local;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import today.parkh.ainimai.image.saver.ImageSaver;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class LocalImageSaver implements ImageSaver {

    private final ImageUtils imageUtils;
    public static final String BASE_URL = "TODO 자기외부ip주소로변경";

    public String base64ImageToUrl(String encodedImage) {
        String storedName = imageUtils.saveImage(encodedImage);
        String readImageUrl = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .path("/image")
                .path("/" + storedName)
                .build().toUriString();

        return readImageUrl;
    }
}
