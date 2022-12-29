package today.parkh.ainimai.comment.dto.response;

import lombok.Getter;
import lombok.Setter;
import today.parkh.ainimai.post.dto.vo.Post;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GetPostList {
    private List<PostId> data = new ArrayList<>();

}
