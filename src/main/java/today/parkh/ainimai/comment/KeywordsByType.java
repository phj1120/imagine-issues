package today.parkh.ainimai.comment;

import lombok.Getter;
import today.parkh.ainimai.comment.dto.vo.KeywordType;

import java.util.ArrayList;
import java.util.List;

@Getter
public class KeywordsByType {
    private List<String> who = new ArrayList<>();
    private List<String> when = new ArrayList<>();
    private List<String> what = new ArrayList<>();
    private List<String> where = new ArrayList<>();

    public void add(Keyword keyword) {
        if (keyword.getType() == KeywordType.WHO) {
            who.add(keyword.getWord());
        } else if (keyword.getType() == KeywordType.WHAT) {
            what.add(keyword.getWord());
        } else if (keyword.getType() == KeywordType.WHEN) {
            when.add(keyword.getWord());
        } else if (keyword.getType() == KeywordType.WHERE) {
            where.add(keyword.getWord());
        }
    }
}
