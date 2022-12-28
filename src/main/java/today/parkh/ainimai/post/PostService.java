package today.parkh.ainimai.post;

import today.parkh.ainimai.post.dto.vo.Post;

public interface PostService {
    Post publishSinglePost(String imageUrl, String content);
}
