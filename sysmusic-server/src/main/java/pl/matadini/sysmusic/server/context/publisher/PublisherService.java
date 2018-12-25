package pl.matadini.sysmusic.server.context.publisher;

import pl.matadini.sysmusic.server.context.publisher.dto.PublisherAddressDto;
import pl.matadini.sysmusic.server.context.publisher.dto.PublisherDto;

import java.util.List;

public interface PublisherService {

    Long addPublisher(String name, PublisherAddressDto publisherAddressDto) throws PublisherServiceException;

    List<PublisherDto> getPublishers() throws PublisherServiceException;

    PublisherDto getPublisher(Long publisherId) throws PublisherServiceException;

    void removePublisher(Long publisherId) throws PublisherServiceException;

    void addRecord(Long publisherId, Long recordId) throws PublisherServiceException;

}
