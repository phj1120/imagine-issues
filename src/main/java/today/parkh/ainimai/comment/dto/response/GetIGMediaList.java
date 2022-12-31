package today.parkh.ainimai.comment.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GetIGMediaList {
    private List<IGMediaId> data = new ArrayList<>();

}
