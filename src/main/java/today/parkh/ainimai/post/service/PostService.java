package today.parkh.ainimai.post.service;

import today.parkh.ainimai.comment.dto.Prompt;
import today.parkh.ainimai.post.dto.Post;

public interface PostService {
    Post publishSinglePost(Prompt content);
}
