package vn.letsgo.elearning.entity.global;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.springframework.stereotype.Component;
import vn.letsgo.elearning.entity.user.User;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Embeddable
@Data
public class AuditMetaData {

    @CreatedDate
    @Column(
            name = "created_date",
            nullable = false,
            updatable = false
    )
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(
            name = "last_modified_date",
            insertable = false
    )
    private LocalDateTime lastModifiedDate;


    @CreatedBy
    @Column(
            name = "created_by",
//            nullable = false,
            updatable = false
    )
    private User createdBy;

    @LastModifiedBy
    @Column(
            name = "last_modified_by",
            insertable = false
    )
    private User lastModifiedBy;
}
