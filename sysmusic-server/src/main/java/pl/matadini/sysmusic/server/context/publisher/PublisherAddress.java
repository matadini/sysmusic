package pl.matadini.sysmusic.server.context.publisher;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.matadini.sysmusic.server.common.CommonConst;

import javax.persistence.Embeddable;


@Data
@Embeddable
@NoArgsConstructor
class PublisherAddress {

    private String postcode;
    private String city;
    private String street;
    private String apartamentNumber;

    static PublisherAddress createEmpty() {
        PublisherAddress publisherAddress = new PublisherAddress();
        publisherAddress.setCity(CommonConst.EMPTY_STRING);
        publisherAddress.setStreet(CommonConst.EMPTY_STRING);
        publisherAddress.setPostcode(CommonConst.EMPTY_STRING);
        publisherAddress.setApartamentNumber(CommonConst.EMPTY_STRING);
        return publisherAddress;
    }
}
