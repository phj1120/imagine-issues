package today.parkh.ainimai.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ImageData implements Image {
    private String data;

    @Override
    public String getImage() {
        return data;
    }

    @Override
    public ImageType getImageType() {
        return ImageType.DATA;
    }
}
