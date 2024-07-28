package sb.application;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sb.domain.Post;
import sb.domain.PostRepository;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public PostService(PostRepository postRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPosts() {
        List<Post> posts = postRepository.findAll();

        return postMapper.toResponses(posts);
    }

    @Transactional(readOnly = true)
    public PostResponse getPost(long id) {
        Post post = postRepository.getById(id);

        return postMapper.toResponse(post);
    }

    @Transactional
    public PostResponse createPost(PostRequest postRequest) {
        Post post = postMapper.toEntity(postRequest);
        Post savedPost = postRepository.save(post);

        return postMapper.toResponse(savedPost);
    }

    @Transactional
    public void deletePost(long id) {
        Post post = postRepository.getById(id);

        postRepository.delete(post);
    }
}
