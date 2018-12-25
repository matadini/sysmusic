package pl.matadini.sysmusic.server.common.spark;

import lombok.Builder;
import lombok.ToString;
import lombok.Value;

@Value
@Builder
@ToString
public class SparkControllerResponse {
    Integer status;
    String message;
    Object object;

    public static SparkControllerResponse empty() {
        return SparkControllerResponse.builder().build();
    }
}
