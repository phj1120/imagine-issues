package today.parkh.ainimai.post.dto.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Post {
    String imageUrl;
    String content;

    public Post(String imageUrl, String content) {
        this.imageUrl = imageUrl;
        this.content = content;
    }
}
