package today.parkh.imagineissues;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import today.parkh.imagineissues.image.KarloService;
import today.parkh.imagineissues.post.InstagramService;
import today.parkh.imagineissues.post.dto.Post;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class TotalService {
    private final InstagramService instagramService;
    private final KarloService karloService;

    public Post posting(String keyword) throws IOException {
        String imageUrl = karloService.makeImageUrl(keyword);
        Post post = instagramService.publishPost(imageUrl, keyword);

        return post;
    }

}
