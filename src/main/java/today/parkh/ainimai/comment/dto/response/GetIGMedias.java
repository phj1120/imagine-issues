package today.parkh.ainimai.comment.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

// media 자체가 복수지만, 자료형을 이름에 적지 않기 위해, 통일 되게 생성하기 위해 medias 라고 이름 지음
@Getter
@Setter
public class GetIGMedias {
    private List<IGMedia> data = new ArrayList<>();

}
