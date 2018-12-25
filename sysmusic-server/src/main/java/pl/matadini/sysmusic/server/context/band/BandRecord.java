package pl.matadini.sysmusic.server.context.band;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "records")
class BandRecord {

    @Id
    @GeneratedValue
    @Column(nullable = false, unique = true)
    private Long recordId;

    private String title;

    public BandRecord(String title) {
        this.title = title;
    }

    private void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    private void setTitle(String title) {
        this.title = title;
    }
}
