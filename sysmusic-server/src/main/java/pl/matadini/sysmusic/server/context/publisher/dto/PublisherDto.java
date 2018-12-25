package pl.matadini.sysmusic.server.context.publisher.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PublisherDto {
    private String name;
    private PublisherAddressDto address;
}
