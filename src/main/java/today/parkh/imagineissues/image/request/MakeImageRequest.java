package today.parkh.imagineissues.image.request;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MakeImageRequest {
    MakeImagePrompt prompt;

    public MakeImageRequest(MakeImagePrompt prompt) {
        this.prompt = prompt;
    }
}
