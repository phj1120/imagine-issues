package today.parkh.ainimai.comment.instagram;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import today.parkh.ainimai.comment.CommentService;
import today.parkh.ainimai.comment.Prompt;
import today.parkh.ainimai.comment.dto.response.GetPostList;
import today.parkh.ainimai.comment.dto.response.PostId;
import today.parkh.ainimai.comment.dto.vo.Post;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
        return null;
    }

    // 게시글 목록 읽어와 전날 게시글 번호 조회
    public List<String> getPostList() {
        String postListUri = UriComponentsBuilder
                .fromHttpUrl(rootDomain)
                .path("/" + igUserId)
                .path("/media")
                .queryParam("access_token", igAccessToken)
                .build().toUriString();

        ResponseEntity<GetPostList> response = new RestTemplate().getForEntity(postListUri, GetPostList.class);
        GetPostList body = response.getBody();

        return body.getData().stream().map(postId -> postId.getId()).collect(Collectors.toList());
    }
}
