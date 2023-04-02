package today.parkh.ainimai.comment.sevice.instagram;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import today.parkh.ainimai.comment.sevice.CommentService;
import today.parkh.ainimai.comment.dto.Keyword;
import today.parkh.ainimai.comment.dto.KeywordsByType;
import today.parkh.ainimai.comment.dto.Prompt;
import today.parkh.ainimai.comment.dto.response.GetIGComments;
import today.parkh.ainimai.comment.dto.response.GetIGMedias;
import today.parkh.ainimai.comment.dto.response.IGComment;
import today.parkh.ainimai.comment.dto.response.IGMedia;
import today.parkh.ainimai.keyword.DefaultKeyword;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class InstagramCommentService implements CommentService {

    @Value("${instagram.rootDomain}")
    private String rootDomain;

    @Value("${instagram.id.user}")
    private String igUserId;

    @Value("${instagram.accessToken}")
    private String igAccessToken;

    // 게시글 목록 읽어와 전날 게시글 번호 조회
    // 전날 게시글의 댓글들 전부 가져와
    // 지정된 명령어에 해당하는 경우, 각각 List 에 담고,
    // 각각 랜덤한 값 하나 뽑아서, 이미지 출력하는 프롬프트 제작
    @Override
    public Prompt generatePrompt() {
        IGMedia recentIGMedia = getRecentIGMedia();
        List<IGComment> simpleComments = getSimpleComments(recentIGMedia.getId());
        KeywordsByType keywordsByType = new KeywordsByType();

        for (IGComment simpleComment : simpleComments) {
            String text = simpleComment.getText();
            Pattern pattern = Pattern.compile("(##)(.*?)(##)");
            Matcher matcher = pattern.matcher(text);
            while (matcher.find()) {
                String group = matcher.group();
                String[] datas = group.split("#");
                for (String typeAndWord : datas) {
                    Keyword keyword = new Keyword(typeAndWord.trim());
                    keywordsByType.add(keyword);
                }
            }
        }

        List<String> whoKeywords = keywordsByType.getWho();
        List<String> whenKeywords = keywordsByType.getWhen();
        List<String> whereKeywords = keywordsByType.getWhere();
        List<String> whatKeywords = keywordsByType.getWhat();

        if (whoKeywords.isEmpty()) {
            whoKeywords.addAll(DefaultKeyword.who);
        }
        if (whenKeywords.isEmpty()) {
            whenKeywords.addAll(DefaultKeyword.when);
        }
        if (whatKeywords.isEmpty()) {
            whatKeywords.addAll(DefaultKeyword.what);
        }
        if (whereKeywords.isEmpty()) {
            whereKeywords.addAll(DefaultKeyword.where);
        }

        String who = keywordsByType.getWho().get(getRandomIndexInRange(whoKeywords));
        String when = keywordsByType.getWhen().get(getRandomIndexInRange(whenKeywords));
        String where = keywordsByType.getWhere().get(getRandomIndexInRange(whereKeywords));
        String what = keywordsByType.getWhat().get(getRandomIndexInRange(whatKeywords));

        Prompt prompt = new Prompt(who, when, where, what);

        return prompt;
    }

    private static int getRandomIndexInRange(List<String> list) {
        Random random = new Random();

        return random.nextInt(list.size());
    }

    // 게시글 목록 읽어와 전날 게시글 번호 조회
    public List<IGMedia> getIGMedias() {
        String postListUri = UriComponentsBuilder
                .fromHttpUrl(rootDomain)
                .path("/" + igUserId)
                .path("/media")
                .queryParam("access_token", igAccessToken)
                .build().toUriString();

        ResponseEntity<GetIGMedias> response = new RestTemplate().getForEntity(postListUri, GetIGMedias.class);
        GetIGMedias body = response.getBody();
        List<IGMedia> data = body.getData();

        return data;
    }

    public IGMedia getRecentIGMedia() {
        List<IGMedia> posts = getIGMedias();
        if (posts.isEmpty()) {
            throw new IllegalArgumentException();
        }
        IGMedia recentIGMedia = posts.get(0);

        return recentIGMedia;
    }

    public List<IGComment> getSimpleComments(String igMediaId) {
        String uri = UriComponentsBuilder
                .fromHttpUrl(rootDomain)
                .path("/" + igMediaId)
                .path("/comments")
                .queryParam("access_token", igAccessToken)
                .build().toUriString();

        ResponseEntity<GetIGComments> response = new RestTemplate().getForEntity(uri, GetIGComments.class);
        GetIGComments body = response.getBody();
        List<IGComment> data = body.getData();

        return data;
    }

    public IGComment getComment(String igCommentId) {
        String fields = "id,user,username,text,timestamp";
        String uri = UriComponentsBuilder
                .fromHttpUrl(rootDomain)
                .path("/" + igCommentId)
                .queryParam("fields", fields)
                .queryParam("access_token", igAccessToken)
                .build().toUriString();

        ResponseEntity<IGComment> response = new RestTemplate().getForEntity(uri, IGComment.class);
        IGComment igComment = response.getBody();

        return igComment;
    }


}
