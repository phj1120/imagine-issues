package today.parkh.ainimai.keyword;

import lombok.Getter;

import java.util.List;

@Getter
public class DefaultKeyword {
    public static List<String> who = List.of("a cat", "a dog", "a rabbit", "a hamster");
    public static List<String> what = List.of("run", "sleep", "watch", "stretch");
    public static List<String> when = List.of("Morning", "night", "day", "winter", "summer", "spring", "fall", "sunset");
    public static List<String> where = List.of("Field", "house", "sofa", "sky", "mountain", "sea", "tree");
}
