package today.parkh.ainimai.post;

import today.parkh.ainimai.comment.Prompt;
import today.parkh.ainimai.post.dto.Image;
import today.parkh.ainimai.post.dto.vo.Post;

public interface PostService {
    Post publishSinglePost(Prompt content);
}
