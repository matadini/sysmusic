package pl.matadini.sysmusic.server.context.band;

import pl.matadini.sysmusic.server.context.band.dto.BandDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BandService {

    Long addBand(String name, LocalDate startDate) throws BandServiceException;

    List<BandDto> getBands() throws BandServiceException;

    Optional<BandDto> getBandById(Long bandId) throws BandServiceException;

    Long addRecord(Long bandId, String title) throws BandServiceException;

    void removeRecord(Long bandId, String title) throws BandServiceException;
}
