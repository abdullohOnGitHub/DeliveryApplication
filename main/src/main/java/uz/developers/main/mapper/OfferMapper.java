package uz.developers.main.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.developers.main.dto.OfferDto;
import uz.developers.main.dto.RequestDto;
import uz.developers.main.entity.District;
import uz.developers.main.entity.Offer;
import uz.developers.main.entity.Request;
import uz.developers.main.repo.DistrictRepo;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OfferMapper {

    private final DistrictRepo districtRepo;

    public Offer toEntity(OfferDto offerDto) throws Exception {
        Optional<District> district = districtRepo.findByName(offerDto.getDistrict_name());
        if (district.isPresent()) {
            return new Offer(offerDto.getId(), offerDto.getOffer_uuid(), district.get(), offerDto.getProduct_uuid());
        } else {
            throw new Exception("District is not present");
        }
    }

    public OfferDto toDto(Offer offer) {
        return new OfferDto(offer.getId(), offer.getOfferUuid(), offer.getDistrict().getName(), offer.getProductUuid());
    }

    public List<OfferDto> toDto(List<Offer> offers) {
        List<OfferDto> offerDtos = null;
        if (offers != null) {
            for (Offer offer : offers) {
                offerDtos.add(toDto(offer));
            }
        }
        return offerDtos;
    }

}
