package vn.letsgo.elearning.entity.asset;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import vn.letsgo.elearning.entity.global.AuditEntity;

import java.util.List;

import static vn.letsgo.elearning.entity.asset.AssetCycleStatus.DELETED;
import static vn.letsgo.elearning.entity.asset.AssetCycleStatus.PENDING;
import static vn.letsgo.elearning.entity.asset.AssetDisplayStatus.HIDDEN;

@Entity
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "asset")
public abstract class Asset extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "asset_id")
    protected Long id;

    @Column(name = "cycle_status")
    @Enumerated(value = EnumType.STRING)
    protected AssetCycleStatus cycleStatus;

    @Column(name = "display_status")
    protected AssetDisplayStatus displayStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bin_id")
    protected RecycleBin recycleBin;

    @Builder.Default
    @Column(name = "number_enrollment")
    protected int numberEnrollment = 0;

    //== Association Assist Methods ==//
    public void updateDeleteStatus() {
        this.setDisplayStatus(HIDDEN);
        this.setCycleStatus(DELETED);
    }

    //== CREATE METHOD ==//
    public static Asset createAsset() {
        return Asset.builder()
                .cycleStatus(PENDING)
                .displayStatus(HIDDEN)
                .build();
    }

}
