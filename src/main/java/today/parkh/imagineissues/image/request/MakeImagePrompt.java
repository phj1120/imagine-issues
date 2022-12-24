package today.parkh.imagineissues.image.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MakeImagePrompt {
    String text;
    int batch_size;

    public MakeImagePrompt(String text, int batch_size) {
        this.text = text;
        this.batch_size = batch_size;
    }
}
