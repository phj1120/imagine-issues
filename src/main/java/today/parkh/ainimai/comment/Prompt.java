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

    public String getString() {
        return who + ", " + when + ", " + what + ", " + where + ", by pixar art";
    }
}
