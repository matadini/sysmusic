package pl.matadini.sysmusic.common.contract.band;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AddRecord {
    Long bandId;
    String title;
}
