package pl.matadini.sysmusic.client.band;

import pl.matadini.sysmusic.common.contract.band.AddBand;
import pl.matadini.sysmusic.common.contract.band.AddRecord;
import pl.matadini.sysmusic.common.contract.band.Band;

import java.util.List;


public interface SysmusicBandClient {

    Long addBand(AddBand data) throws SysmusicBandClientException;

    List<Band> getBands() throws SysmusicBandClientException;

    Band getBandById(Long bandId) throws SysmusicBandClientException;

    Long addRecord(AddRecord data) throws SysmusicBandClientException;

}
