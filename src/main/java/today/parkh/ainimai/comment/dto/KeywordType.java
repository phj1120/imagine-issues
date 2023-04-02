package today.parkh.ainimai.comment.dto;

public enum KeywordType {
    WHO("who"), WHEN("when"), WHAT("what"), WHERE("where"), OTHER("other");

    String name;

    KeywordType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
