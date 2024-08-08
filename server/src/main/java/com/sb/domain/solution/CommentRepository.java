package com.sb.domain.solution;

import java.util.List;
import com.sb.infra.exception.ExceptionType;
import com.sb.infra.exception.SbException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllBySolution_Id(Long solutionId);

    default Comment getCommentById(Long id) {
        return findById(id)
                .orElseThrow(() -> new SbException(ExceptionType.COMMENT_NOT_FOUND));
    }
}
