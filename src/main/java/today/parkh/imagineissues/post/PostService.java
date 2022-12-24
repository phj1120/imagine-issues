package today.parkh.imagineissues.post;

import today.parkh.imagineissues.post.dto.vo.Post;

public interface PostService {
    Post publishSinglePost(String imageUrl, String content);
}
