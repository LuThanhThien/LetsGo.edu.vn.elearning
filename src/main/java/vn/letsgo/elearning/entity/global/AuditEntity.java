package vn.letsgo.elearning.entity.global;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import vn.letsgo.elearning.entity.user.User;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditEntity {

    @CreatedDate
    @Column(
            name = "created_datetime",
            nullable = false,
            updatable = false
    )
    protected LocalDateTime createdDatetime;

    @LastModifiedDate
    @Column(
            name = "last_modified_datetime",
            insertable = false
    )
    protected LocalDateTime lastModifiedDatetime;


    @CreatedBy
    @Column(
            name = "created_by",
//            nullable = false,
            updatable = false
    )
    protected User createdBy;

    @LastModifiedBy
    @Column(
            name = "last_modified_by",
            insertable = false
    )
    protected User lastModifiedBy;
}
