package sb.application;

import java.util.List;
import org.springframework.stereotype.Component;
import sb.domain.Post;

@Component
public class PostMapper {

    public PostResponse toResponse(Post post) {
        return new PostResponse(post.getId(), post.getTitle(), post.getContent());
    }

    public List<PostResponse> toResponses(List<Post> posts) {
        return posts.stream()
                .map(this::toResponse)
                .toList();
    }

    public Post toEntity(PostRequest postRequest) {
        return new Post(postRequest.title(), postRequest.content());
    }
}
