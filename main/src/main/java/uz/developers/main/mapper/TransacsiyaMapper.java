package uz.developers.main.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.developers.main.dto.TransacsiyaDto;
import uz.developers.main.entity.Carrier;
import uz.developers.main.entity.Offer;
import uz.developers.main.entity.Request;
import uz.developers.main.entity.Transacsiya;
import uz.developers.main.repo.CarrierRepo;
import uz.developers.main.repo.OfferRepo;
import uz.developers.main.repo.RequestRepo;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TransacsiyaMapper {
    private final CarrierRepo carrierRepo;
    private final RequestRepo requestRepo;
    private final OfferRepo offerRepo;

    public Transacsiya toEntity(TransacsiyaDto transacsiyaDto) throws Exception {
        Optional<Carrier> carrier = carrierRepo.findByFirstName(transacsiyaDto.getCarrier_name());
        Optional<Request> request = requestRepo.findByRequestUuid(transacsiyaDto.getRequest_uuid());
        Optional<Offer> offer = offerRepo.findByOfferUuid(transacsiyaDto.getOffer_uuid());
        if (carrier.isPresent() && request.isPresent() && offer.isPresent()) {
            return new Transacsiya(transacsiyaDto.getId(), transacsiyaDto.getTransacsiya_uuid(), carrier.get(), request.get(), offer.get(), transacsiyaDto.getScore());

        } else if (!carrier.isPresent() && request.isPresent() && offer.isPresent()) {
            throw new Exception("Carrier is not present");
        } else if (!request.isPresent() && carrier.isPresent() && offer.isPresent()) {
            throw new Exception("Request is not present");
        } else {
            throw new Exception("Offer is not present");
        }
    }

    public TransacsiyaDto toDto(Transacsiya transacsiya) {
        return new TransacsiyaDto(transacsiya.getId(), transacsiya.getTransacsiyaUuid(), transacsiya.getCarrier().getFirstName(), transacsiya.getRequest().getRequestUuid(), transacsiya.getOffer().getOfferUuid(), transacsiya.getScore());
    }

    public List<TransacsiyaDto> toDto(List<Transacsiya> transacsiyas) {
        List<TransacsiyaDto> transacsiyaDtos = null;
        if (transacsiyas != null) {
            for (Transacsiya transacsiya : transacsiyas) {
                transacsiyaDtos.add(toDto(transacsiya));
            }
        }
        return transacsiyaDtos;
    }
}
