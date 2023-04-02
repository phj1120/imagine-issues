package today.parkh.ainimai.image.converter.local;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import today.parkh.ainimai.common.utils.ImageUtils;
import today.parkh.ainimai.image.converter.ImageConverter;
import today.parkh.ainimai.post.dto.ImageData;
import today.parkh.ainimai.post.dto.ImageUrl;

@Slf4j
@Service
@RequiredArgsConstructor
public class LocalImageConverter implements ImageConverter {

    private final ImageUtils imageUtils;
    public static final String BASE_URL = "TODO 자기외부ip주소로변경";

    @Override
    public ImageUrl dataToUrl(ImageData originalImage) {
        String imageData = originalImage.getImage();
        String storedName = imageUtils.saveImage(imageData);
        String readableImageUrl = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .path("/image")
                .path("/" + storedName)
                .build().toUriString();
        ImageUrl newImage = new ImageUrl(readableImageUrl);

        return newImage;
    }

    @Override
    public ImageData urlToData(ImageUrl imageUrl) {
        return null;
    }

}
