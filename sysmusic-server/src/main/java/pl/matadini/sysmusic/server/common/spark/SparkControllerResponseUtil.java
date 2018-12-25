package pl.matadini.sysmusic.server.common.spark;

import org.eclipse.jetty.http.HttpStatus;
import pl.matadini.sysmusic.server.common.spark.SparkControllerResponse;

public class SparkControllerResponseUtil {

    public static SparkControllerResponse createErrorResponse(Exception ex) {
        return SparkControllerResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR_500)
                .message(ex.getMessage())
                .build();
    }

    private SparkControllerResponseUtil() {

    }
}
