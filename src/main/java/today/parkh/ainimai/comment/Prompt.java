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

    private Prompt(String who, String when, String where, String what) {
        this.who = who;
        this.when = when;
        this.where = where;
        this.what = what;
    }
}
