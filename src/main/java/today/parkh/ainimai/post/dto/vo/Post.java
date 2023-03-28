package today.parkh.ainimai.post.dto.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import today.parkh.ainimai.post.dto.Image;

@Getter
@Setter
@NoArgsConstructor
public class Post {
    Image image;
    private String content;

    public Post(Image image, String content) {
        this.image = image;
        this.content = content;
    }
}
