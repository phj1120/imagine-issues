package today.parkh.ainimai.comment;

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

    @Override
    public String toString() {
        return who + ", " + when + ", " + what + ", " + where + ", by pixar art";
    }

    public String toContentString() {
        String content = "Today's Art\n";
        content += "[ Who ] : " + who + "\n";
        content += "[Where] : " + where + "\n";
        content += "[When ] : " + when + "\n";
        content += "[ What ] : " + what + "\n";
        content += "\n";
        content += "#aigenerateart #fantasyart #animalart #aiartdailytheme #generativeart #aiartwork #animallovers";

        return content;
    }
}
