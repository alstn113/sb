package com.sb.infra.persistence;


import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Getter
@ToString(callSuper = true)
public abstract class CreatedAtAuditableEntity extends IdentifiableEntity {

    @CreatedDate
    @Column(nullable = false, updatable = false)
    protected LocalDateTime createdAt;

    protected CreatedAtAuditableEntity(Long id) {
        super(id);
    }
}
