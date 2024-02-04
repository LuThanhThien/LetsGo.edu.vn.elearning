package vn.letsgo.elearning.entity.asset;

import jakarta.persistence.*;
import lombok.*;
import org.checkerframework.checker.units.qual.A;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import vn.letsgo.elearning.entity.global.AuditEntity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static vn.letsgo.elearning.entity.asset.AssetCycleStatus.DELETED;
import static vn.letsgo.elearning.entity.asset.AssetDisplayStatus.HIDDEN;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "recycle_bin")
public class RecycleBin extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bin_id")
    private Long id;

    @Builder.Default
    @OneToMany(mappedBy = "recycleBin", cascade = CascadeType.ALL)
    private Set<Asset> assets = new HashSet<>();

    //== Create Method ==//
    public static void deleteAsset(Asset asset) {
        RecycleBin recycleBin = new RecycleBin();
        recycleBin.assets.add(asset);
        asset.setRecycleBin(recycleBin);
        asset.updateDeleteStatus();
    }

}
