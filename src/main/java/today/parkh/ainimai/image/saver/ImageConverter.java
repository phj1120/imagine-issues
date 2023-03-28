package today.parkh.ainimai.image.saver;

import today.parkh.ainimai.post.dto.ImageData;
import today.parkh.ainimai.post.dto.ImageUrl;

public interface ImageConverter {
    ImageUrl dataToUrl(ImageData imageData);

    ImageData urlToData(ImageUrl imageUrl);
}
