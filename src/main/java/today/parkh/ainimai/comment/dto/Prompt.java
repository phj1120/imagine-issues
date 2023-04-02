package today.parkh.ainimai.comment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Prompt {
    private String who;
    private String when;
    private String where;
    private String what;

    public Prompt(String who, String when, String where, String what) {
        this.who = who;
        this.when = when;
        this.where = where;
        this.what = what;
    }

    public String getPromptText() {
        return who + ", " + when + ", " + what + ", " + where + ", by pixar art";
    }

    public String getContent() {
        String content = "Today's Art\n";
        content += "[ Who ] : " + who + "\n";
        content += "[Where] : " + where + "\n";
        content += "[When ] : " + when + "\n";
        content += "[ What ] : " + what + "\n";
        content += "\n";
        content += "Please leave a comment on the picture you want to see according to the format\n" +
                "Images are created based on comments\n\n" +
                "Example)\n" +
                "##who a dog # when sunny day # where garden # what sleep##\n\n";
        content += "#aigenerateart #fantasyart #animalart #aiartdailytheme #generativeart #aiartwork #animallovers";

        return content;
    }
}
