package today.parkh.imagineissues.post.instagram.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class CheckPublishLimitResponse {
    List<DataResponse> data;
}

