package pl.matadini.sysmusic.server.context.publisher;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@NoArgsConstructor
@Table(name = "records")
class PublisherRecord {

    @Id
    @GeneratedValue
    @Column(nullable = false, unique = true)
    Long recordId;

    String title;

    public PublisherRecord(String title) {
        this.title = title;
    }

    private void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    private void setTitle(String title) {
        this.title = title;
    }

}
