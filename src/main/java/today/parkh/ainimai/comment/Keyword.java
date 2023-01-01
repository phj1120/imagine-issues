package today.parkh.ainimai.comment;

import lombok.Getter;
import today.parkh.ainimai.comment.dto.vo.KeywordType;

@Getter
public class Keyword {
    private String word;
    private KeywordType type;

    public Keyword(String typeAndWord) {
        String[] datas = typeAndWord.split(" ");
        String keywordTypeString = datas[0];
        String word = typeAndWord.replaceFirst(keywordTypeString, "");

        KeywordType keywordType;
        if (keywordTypeString.equals(KeywordType.WHO.getName())) {
            keywordType = KeywordType.WHO;
        } else if (keywordTypeString.equals(KeywordType.WHEN.getName())) {
            keywordType = KeywordType.WHEN;
        } else if (keywordTypeString.equals(KeywordType.WHAT.getName())) {
            keywordType = KeywordType.WHAT;
        } else if (keywordTypeString.equals(KeywordType.WHERE.getName())) {
            keywordType = KeywordType.WHERE;
        } else {
            keywordType = KeywordType.OTHER;
        }

        this.word = word;
        this.type = keywordType;
    }
}
