package today.parkh.ainimai.comment;

import today.parkh.ainimai.comment.dto.vo.Comment;

import java.time.LocalDateTime;

public class InstagramComment extends Comment {

    public InstagramComment(String id, String text, LocalDateTime timestamp) {
        super(id, text, timestamp);
    }
}
