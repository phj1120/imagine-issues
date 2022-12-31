package today.parkh.ainimai.comment.dto.response;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GetIGCommentList {
    List<IGComment> data = new ArrayList<>();
}
