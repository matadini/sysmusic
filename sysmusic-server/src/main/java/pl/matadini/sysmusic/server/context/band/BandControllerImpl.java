package pl.matadini.sysmusic.server.context.band;

import com.google.gson.Gson;
import lombok.Builder;
import org.eclipse.jetty.http.HttpStatus;
import org.modelmapper.ModelMapper;
import org.pmw.tinylog.Logger;
import pl.matadini.sysmusic.common.contract.band.AddBand;
import pl.matadini.sysmusic.common.contract.band.AddRecord;
import pl.matadini.sysmusic.common.contract.band.Band;
import pl.matadini.sysmusic.common.contract.band.BandControllerUrl;
import pl.matadini.sysmusic.server.common.spark.SparkControllerResponse;
import pl.matadini.sysmusic.server.common.spark.SparkControllerResponseUtil;
import pl.matadini.sysmusic.server.context.band.dto.BandDto;
import spark.Request;
import spark.Response;
import spark.Service;

import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Builder
class BandControllerImpl implements BandController {

    Gson gson;
    ModelMapper modelMapper;
    EntityManagerFactory entityManagerFactory;

    @Override
    public Object addBand(Request request, Response response) {

        try {

            AddBand args = gson.fromJson(request.body(), AddBand.class);
            String name = args.getName();
            LocalDate startLocalDate = args.getStartDate();

            BandService service = BandServiceFactory.create(entityManagerFactory);
            Long addBand = service.addBand(name, startLocalDate);

            return SparkControllerResponse.builder()
                    .status(HttpStatus.OK_200)
                    .object(addBand)
                    .build();

        } catch (Exception ex) {
            Logger.error(ex);
            return SparkControllerResponseUtil.createErrorResponse(ex);
        }
    }


    @Override
    public Object getBands(Request request, Response response) {

        try {

            BandService service = BandServiceFactory.create(entityManagerFactory);
            List<BandDto> bands = service.getBands();

            /**
             * Nie ma pewnosci czy DTO kontraktowe jest 1:1 z DTO z serwisu kontekstu
             * przez co wykonujemy mapowanie
             */
            List<Band> contractBands = bands.stream()
                    .map(item -> modelMapper.map(item, Band.class))
                    .collect(Collectors.toList());

            return SparkControllerResponse.builder()
                    .status(HttpStatus.OK_200)
                    .object(contractBands)
                    .build();

        } catch (Exception ex) {
            Logger.error(ex);
            return SparkControllerResponseUtil.createErrorResponse(ex);
        }
    }

    @Override
    public Object getBandById(Request request, Response response) {
        try {

            Long bandId = Long.valueOf(request.queryParams("band_id"));

            BandService service = BandServiceFactory.create(entityManagerFactory);
            Optional<BandDto> optionalBandDto = service.getBandById(bandId);
            if(optionalBandDto.isPresent()) {

                Band band = modelMapper.map(optionalBandDto.get(), Band.class);
                return SparkControllerResponse.builder()
                        .object(band)
                        .status(HttpStatus.OK_200)
                        .build();
            } else {
                return SparkControllerResponse.builder()
                        .status(HttpStatus.NO_CONTENT_204)
                        .build();
            }


        } catch (Exception ex) {
            Logger.error(ex);
            return SparkControllerResponseUtil.createErrorResponse(ex);
        }

    }

    @Override
    public Object addRecord(Request request, Response response) {

        try {
            AddRecord addRecord = gson.fromJson(request.body(), AddRecord.class);
            Long bandId = addRecord.getBandId();
            String title = addRecord.getTitle();

            BandService service = BandServiceFactory.create(entityManagerFactory);
            Long recordId = service.addRecord(bandId, title);

            return SparkControllerResponse.builder()
                    .status(HttpStatus.OK_200)
                    .object(recordId)
                    .build();

        } catch (Exception ex) {
            Logger.error(ex);
            return SparkControllerResponseUtil.createErrorResponse(ex);
        }
    }

    @Override
    public void initialize(Service service) {

        service.post(BandControllerUrl.ADD_BAND, BandControllerImpl.this::addBand, gson::toJson);
        service.post(BandControllerUrl.ADD_RECORD, BandControllerImpl.this::addRecord, gson::toJson);

        service.get(BandControllerUrl.GET_BANDS, BandControllerImpl.this::getBands, gson::toJson);
        service.get(BandControllerUrl.GET_BAND_BY_ID, BandControllerImpl.this::getBandById, gson::toJson);
    }
}
