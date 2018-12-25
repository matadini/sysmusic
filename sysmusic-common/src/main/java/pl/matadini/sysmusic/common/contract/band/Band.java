package pl.matadini.sysmusic.common.contract.band;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Data
@ToString
public class Band {
    String name;
    LocalDate startDate;
    List<BandRecord> records = Lists.newArrayList();
}
