package pl.matadini.sysmusic.server.context.band;

import pl.matadini.sysmusic.server.common.spark.SparkController;
import spark.Response;

interface BandController extends SparkController {

    Object addBand(spark.Request request, Response response);

    Object getBands(spark.Request request, Response response);

    Object getBandById(spark.Request request, Response response);

    Object addRecord(spark.Request request, Response response);

}

