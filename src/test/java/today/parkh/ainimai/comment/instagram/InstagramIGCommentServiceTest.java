package today.parkh.ainimai.comment.instagram;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import today.parkh.ainimai.comment.Keyword;
import today.parkh.ainimai.comment.KeywordsByType;
import today.parkh.ainimai.comment.Prompt;
import today.parkh.ainimai.comment.dto.response.IGComment;
import today.parkh.ainimai.comment.dto.response.IGMedia;
import today.parkh.ainimai.comment.dto.vo.KeywordType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
class InstagramIGCommentServiceTest {

    @Autowired
    InstagramCommentService instagramCommentService;

    @Test
    public void getIGMedias() {
        List<IGMedia> igMedias = instagramCommentService.getIGMedias();

        Assertions.assertThat(igMedias).isNotEmpty();
    }


    @Test
    public void getRecentIGMedia() {
        IGMedia recentIGMedia = instagramCommentService.getRecentIGMedia();

        Assertions.assertThat(recentIGMedia).isNotNull();
    }

    @Test
    public void getComments() {
        IGMedia recentIGMedia = instagramCommentService.getRecentIGMedia();

        List<IGComment> comments = instagramCommentService.getSimpleComments(recentIGMedia.getId());
        Assertions.assertThat(comments).isNotEmpty();
    }

    @Test
    public void getComment() {
        IGMedia recentIGMedia = instagramCommentService.getRecentIGMedia();

        List<IGComment> simpleComments = instagramCommentService.getSimpleComments(recentIGMedia.getId());
        for (IGComment simpleComment : simpleComments) {
            IGComment comment = instagramCommentService.getComment(simpleComment.getId());
            Assertions.assertThat(comment).isNotNull();
        }
    }


    @Test
    public void generatePrompt() {
        Prompt prompt = instagramCommentService.generatePrompt();

        System.out.println(prompt);
    }

    @Test
    public void getRandomIndexInRange() {
        Random random = new Random();

        List<String> list = new ArrayList<>();
        list.add("hi");

        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextInt(list.size()));
        }
    }

    @Test
    public void regex() {
        String text = "hi ##who a dog # when sunny day # where garden # what sleep## 123";
        Pattern pattern = Pattern.compile("(##)(.*?)(##)");
        Matcher matcher = pattern.matcher(text);
        KeywordsByType keywordsByType = new KeywordsByType();
        while (matcher.find()) {
            String group = matcher.group();
            String[] datas = group.split("#");
            for (String typeAndWord : datas) {
                Keyword keyword = new Keyword(typeAndWord.trim());
                keywordsByType.add(keyword);
            }
        }

        Assertions.assertThat(keywordsByType.getWho()).isNotEmpty();
        Assertions.assertThat(keywordsByType.getWhat()).isNotEmpty();
        Assertions.assertThat(keywordsByType.getWhen()).isNotEmpty();
        Assertions.assertThat(keywordsByType.getWhere()).isNotEmpty();
    }

}