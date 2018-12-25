package pl.matadini.sysmusic.common.contract.band;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
@AllArgsConstructor
public class AddBand {
    String name;
    LocalDate startDate;
}
