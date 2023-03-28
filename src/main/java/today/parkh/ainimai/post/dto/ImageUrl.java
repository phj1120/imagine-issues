package today.parkh.ainimai.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ImageUrl implements Image {
    private String url;

    @Override
    public String getImage() {
        return url;
    }

    @Override
    public ImageType getImageType() {
        return ImageType.DATA;
    }
}
