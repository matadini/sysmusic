package pl.matadini.sysmusic.common.contract.band;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SparkControllerResponse {
    Integer status;
    String message;
    Object object;
}
