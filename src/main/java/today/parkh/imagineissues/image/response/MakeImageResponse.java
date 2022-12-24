package today.parkh.imagineissues.image.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class MakeImageResponse {
    String id;
    String modelVersion;
    List<KarloImage> images = new ArrayList<>();
}
