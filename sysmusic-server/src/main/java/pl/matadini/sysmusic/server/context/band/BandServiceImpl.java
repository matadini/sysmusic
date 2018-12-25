package pl.matadini.sysmusic.server.context.band;

import lombok.Builder;
import org.modelmapper.ModelMapper;
import pl.matadini.sysmusic.server.context.band.dto.BandDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Builder
class BandServiceImpl implements BandService {

    ModelMapper modelMapper;
    BandRepository bandRepository;

    @Override
    public Long addBand(String name, LocalDate startDate) throws BandServiceException {
        Band band = new Band(name, startDate);
        band = bandRepository.save(band);
        return band.getBandId();
    }

    @Override
    public List<BandDto> getBands() throws BandServiceException {

        List<Band> all = bandRepository.findAll();
        return all.stream().map(item -> modelMapper.map(item, BandDto.class)).collect(Collectors.toList());
    }

    @Override
    public Optional<BandDto> getBandById(Long bandId) throws BandServiceException {

        Optional<Band> optionalBand = bandRepository.findById(bandId);
        return optionalBand.isPresent() ?
                Optional.ofNullable(modelMapper.map(optionalBand.get(), BandDto.class))
                : Optional.empty();
    }

    @Override
    public Long addRecord(Long bandId, String title) throws BandServiceException {

        Optional<Band> bandOpt = bandRepository.findById(bandId);
        if (bandOpt.isPresent()) {

            Band band = bandOpt.get();
            band.addRecord(title);
            bandRepository.save(band);
            return band.findRecordByTitle(title).get().getRecordId();

        } else {
            throw new BandServiceException("Brak w bazie zespolu o id: " + bandId);
        }
    }

    @Override
    public void removeRecord(Long bandId, String title) throws BandServiceException {

        Optional<Band> optionalBand = bandRepository.findById(bandId);
        if(optionalBand.isPresent()) {

            Band band = optionalBand.get();
            band.removeRecord(title);
            bandRepository.save(band);

        } else {
            throw new BandServiceException("Brak w bazie zespolu o id: " + bandId);
        }

    }
}
