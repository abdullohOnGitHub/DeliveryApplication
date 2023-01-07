package uz.developers.main.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.developers.main.dto.OfferDto;
import uz.developers.main.dto.RequestDto;
import uz.developers.main.dto.ResponseDto;
import uz.developers.main.entity.Offer;
import uz.developers.main.entity.Request;
import uz.developers.main.mapper.OfferMapper;
import uz.developers.main.repo.OfferRepo;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OfferService {

    private final OfferRepo offerRepo;
    private final OfferMapper offerMapper;

    public ResponseDto addRequest(OfferDto offerDto) throws Exception {
        Offer offer = offerMapper.toEntity(offerDto);
        if (offerRepo.existsByOfferUuid(offer.getOfferUuid())) {
            return new ResponseDto(-1, "Offer already created", null);
        }
        return ResponseDto.getSuccess(offerMapper.toDto(offerRepo.save(offer)));
    }

    public ResponseDto getAllRequest() {
        return ResponseDto.getSuccess(offerMapper.toDto(offerRepo.findAll()));
    }

    public ResponseDto deleteRequest(Integer id) {
        Optional<Offer> offer = offerRepo.findById(id);
        if (offer.isPresent()) {
            offerRepo.delete(offer.get());
            return ResponseDto.getSuccess(null);
        } else {
            return ResponseDto.UserNotFound();
        }
    }

}
