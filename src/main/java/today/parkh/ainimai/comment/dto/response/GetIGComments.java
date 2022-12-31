package today.parkh.ainimai.comment.dto.response;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GetIGComments {
    List<IGComment> data = new ArrayList<>();
}
