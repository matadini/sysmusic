package pl.matadini.sysmusic.server.context.band;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Getter
@Entity
@ToString
@NoArgsConstructor
class Band {

    @Id
    @GeneratedValue
    @Column(nullable = false, unique = true)
    private Long bandId;

    private String name;

    private LocalDate startDate;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<BandRecord> bandRecords = Lists.newArrayList();

    public Band(String name, LocalDate startDate) {
        this.name = name;
        this.startDate = startDate;
    }

    void removeRecord(String title) {
        Optional<BandRecord> recordByTitle = findRecordByTitle(title);
        if(recordByTitle.isPresent()) {
            bandRecords.remove(recordByTitle.get());
        }
    }

    public Optional<BandRecord> findRecordByTitle(String title) {
        return bandRecords.stream().filter(item -> item.getTitle().equals(title)).findFirst();
    }

    void addRecord(String title) {
        BandRecord bandRecord = new BandRecord(title);
        bandRecords.add(bandRecord);
    }
}
