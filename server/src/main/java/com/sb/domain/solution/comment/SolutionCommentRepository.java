package com.sb.domain.solution.comment;

import java.util.List;
import com.sb.infra.exception.ExceptionType;
import com.sb.infra.exception.SbException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolutionCommentRepository extends JpaRepository<SolutionComment, Long> {

    List<SolutionComment> findAllBySolution_IdOrderByCreatedAtAsc(Long solutionId);

    default SolutionComment getCommentById(Long id) {
        return findById(id)
                .orElseThrow(() -> new SbException(ExceptionType.COMMENT_NOT_FOUND));
    }
}
