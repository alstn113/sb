package sb.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    default Post getById(long id) {
        String message = String.format("Post not found with id: %d", id);

        return findById(id)
                .orElseThrow(() -> new IllegalArgumentException(message));
    }
}
