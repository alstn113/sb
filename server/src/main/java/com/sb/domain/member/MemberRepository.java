package com.sb.domain.member;

import java.util.Optional;
import com.sb.infra.exception.ExceptionType;
import com.sb.infra.exception.SbException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findBySocialIdAndProvider(Long socialId, Provider provider);

    default Member getMemberById(Long id) {
        return findById(id)
                .orElseThrow(() -> new SbException(ExceptionType.MEMBER_NOT_FOUND));
    }
}
