package today.parkh.imagineissues.image.response;

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

