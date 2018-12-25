package pl.matadini.sysmusic.server.context.publisher.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PublisherAddressDto {
    private String postcode;
    private String city;
    private String street;
    private String apartamentNumber;
}
