package pl.matadini.sysmusic.server.context.publisher;

import lombok.Builder;
import org.modelmapper.ModelMapper;
import pl.matadini.sysmusic.server.context.publisher.dto.PublisherAddressDto;
import pl.matadini.sysmusic.server.context.publisher.dto.PublisherDto;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Builder
class PublisherServiceImpl implements PublisherService {

    ModelMapper modelMapper;
    PublisherRepository publisherRepository;
    PublisherRecordCatalog publisherRecordCatalog;

    @Override
    public Long addPublisher(String name, PublisherAddressDto publisherAddressDto) throws PublisherServiceException {
        Publisher publisher = new Publisher(name);
        publisher.changeAddress(modelMapper.map(publisherAddressDto, PublisherAddress.class));
        publisher = publisherRepository.save(publisher);
        return publisher.getPublisherId();
    }

    @Override
    public List<PublisherDto> getPublishers() throws PublisherServiceException {
        return publisherRepository.findAll()
                .stream()
                .map(item -> modelMapper.map(item, PublisherDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PublisherDto getPublisher(Long publisherId) throws PublisherServiceException {
        Optional<Publisher> byId = publisherRepository.findById(publisherId);
        if (byId.isPresent()) {
            return modelMapper.map(byId.get(), PublisherDto.class);
        } else {
            throw new PublisherServiceException();
        }
    }

    @Override
    public void removePublisher(Long publisherId) throws PublisherServiceException {

    }

    @Override
    public void addRecord(Long publisherId, Long recordId) throws PublisherServiceException {
        Optional<PublisherRecord> publisherRecord = publisherRecordCatalog.get(recordId);
        if (!publisherRecord.isPresent()) {
            throw new PublisherServiceException("Brak w katalogu nagran: " + recordId);
        }

        Optional<Publisher> optionalPublisher = publisherRepository.findById(publisherId);
        if (!optionalPublisher.isPresent()) {
            throw new PublisherServiceException("Brak wydawcy: " + publisherId);
        }

        PublisherRecord record = publisherRecord.get();
        Publisher publisher = optionalPublisher.get();
        publisher.addRecord(record);
        publisherRepository.save(publisher);
    }


}
