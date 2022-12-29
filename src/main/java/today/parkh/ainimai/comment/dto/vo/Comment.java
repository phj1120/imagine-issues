package today.parkh.ainimai.comment.dto.vo;

import lombok.Getter;

import java.time.LocalDateTime;

public abstract class Comment {
    String id;
    String text;
    LocalDateTime timestamp;

    public Comment(String id, String text, LocalDateTime timestamp) {
        this.id = id;
        this.text = text;
        this.timestamp = timestamp;
    }
}
