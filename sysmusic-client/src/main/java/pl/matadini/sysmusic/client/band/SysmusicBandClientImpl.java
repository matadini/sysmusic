package pl.matadini.sysmusic.client.band;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import lombok.Builder;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import org.pmw.tinylog.Logger;
import pl.matadini.sysmusic.common.contract.band.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Builder
class SysmusicBandClientImpl implements SysmusicBandClient {

    Gson gson;
    String address;

    @Override
    public Long addBand(AddBand data) throws SysmusicBandClientException {
        try {

            String url = address + BandControllerUrl.ADD_BAND;
            HttpResponse<JsonNode> jsonNodeHttpResponse = Unirest.post(url)
                    .body(gson.toJson(data))
                    .asJson();

            String json = jsonNodeHttpResponse.getBody().toString();
            SparkControllerResponse sparkControllerResponse = gson.fromJson(json, SparkControllerResponse.class);

            /**
             * Jakies magiczne instrukcje sprawiaja ze GSON parsuje
             * integery na liczby zmienno przecinkowe
             */
            return Long.valueOf(Double.valueOf(sparkControllerResponse.getObject().toString()).longValue());

        } catch (Exception ex) {
            throw new SysmusicBandClientException(ex.getMessage());
        }
    }

    @Override
    public List<Band> getBands() throws SysmusicBandClientException {
        try {

            String url = address + BandControllerUrl.GET_BANDS;
            HttpResponse<JsonNode> jsonNodeHttpResponse = Unirest.get(url).asJson();
            SparkControllerResponse sparkControllerResponse = gson.fromJson(
                    jsonNodeHttpResponse.getBody().toString(),
                    SparkControllerResponse.class);

            Type type = new TypeToken<ArrayList<Band>>(){}.getType();
            return gson.fromJson(sparkControllerResponse.getObject().toString(), type);

        } catch (Exception ex) {
            throw new SysmusicBandClientException(ex.getMessage());
        }
    }

    @Override
    public Band getBandById(Long bandId) throws SysmusicBandClientException {
        try {

            HttpResponse<JsonNode> httpResponse = Unirest.get(address + BandControllerUrl.GET_BAND_BY_ID)
                    .queryString("band_id", bandId)
                    .asJson();
            SparkControllerResponse fromJson = gson.fromJson(httpResponse.getBody().toString(), SparkControllerResponse.class);
            return gson.fromJson(fromJson.getObject().toString(), Band.class);
        } catch (Exception ex) {
            throw new SysmusicBandClientException(ex.getMessage());
        }
    }

    @Override
    public Long addRecord(AddRecord data) throws SysmusicBandClientException {
        try {
            return null;
        } catch (Exception ex) {
            throw new SysmusicBandClientException(ex.getMessage());
        }
    }
}
