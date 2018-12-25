package pl.matadini.sysmusic.server.context.publisher;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
class Publisher {

    @Id
    @GeneratedValue
    @Column(nullable = false, unique = true)
    private Long publisherId;

    @Column(nullable = false, unique = true)
    private String name;

    @Embedded
    private PublisherAddress publisherAddress;

    @OneToMany
    private List<PublisherRecord> publisherRecords = Lists.newArrayList();

    public Publisher(String name) {
        this.name = name;
    }

    public void changeAddress(PublisherAddress publisherAddress) {
        this.publisherAddress = publisherAddress;
    }

    public void addRecord(PublisherRecord publisherRecord) {
        if (!publisherRecords.contains(publisherRecord)) {
            publisherRecords.add(publisherRecord);
        }
    }

    private void setPublisherId(Long publisherId) {
        this.publisherId = publisherId;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setPublisherAddress(PublisherAddress publisherAddress) {
        this.publisherAddress = publisherAddress;
    }

    private void setPublisherRecords(List<PublisherRecord> publisherRecords) {
        this.publisherRecords = publisherRecords;
    }
}
