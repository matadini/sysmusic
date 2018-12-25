package pl.matadini.sysmusic.server.context.band.dto;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Data
@ToString
public class BandDto {

    String name;

    LocalDate startDate;

    List<BandRecordDto> records = Lists.newArrayList();
}
