package today.parkh.imagineissues.post.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Post {
    private String id;
    private String caption;
    private String username;
}
