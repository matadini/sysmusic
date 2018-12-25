package pl.matadini.sysmusic.client.band;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SysmusicBandClientFactory {

    public static SysmusicBandClient create(String address) {
        Gson gson = new GsonBuilder()
                .serializeNulls()
                .create();
        return SysmusicBandClientImpl.builder()
                .gson(gson)
                .address(address)
                .build();
    }

    private SysmusicBandClientFactory() {

    }
}
