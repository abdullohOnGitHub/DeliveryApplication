package uz.developers.main.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.developers.main.dto.ResponseDto;
import uz.developers.main.dto.TransacsiyaDto;
import uz.developers.main.entity.Transacsiya;
import uz.developers.main.mapper.TransacsiyaMapper;
import uz.developers.main.repo.TransacsiyaRepo;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransacsiyaService {
    private final TransacsiyaRepo transacsiyaRepo;
    private final TransacsiyaMapper transacsiyaMapper;

    public ResponseDto addTransacsiya(TransacsiyaDto transacsiyaDto) throws Exception {
        Transacsiya transacsiya = transacsiyaMapper.toEntity(transacsiyaDto);
        boolean requestExists = transacsiyaRepo.existsByRequest(transacsiya.getRequest());
        boolean offerExists = transacsiyaRepo.existsByOffer(transacsiya.getOffer());
        if (!requestExists && !offerExists) {
            if (transacsiya.getRequest().getProductUuid().equals(transacsiya.getOffer().getProductUuid())) {
                if (transacsiya.getCarrier().getRegions().getId() == transacsiya.getRequest().getDistrict().getRegion().getId()
                        && transacsiya.getCarrier().getRegions().getId() == transacsiya.getOffer().getDistrict().getRegion().getId()) {
                    return ResponseDto.getSuccess(transacsiyaRepo.save(transacsiya));
                } else {
                    return new ResponseDto(-1, "Carrier delivery places did not matched", null);
                }
            } else {
                return new ResponseDto(-1, "Request's and Offer's productId did not matched", null);
            }
        } else {
            return new ResponseDto(-1, "Request or Offer already used", null);
        }
    }

    public ResponseDto getAll() {
        return ResponseDto.getSuccess(transacsiyaMapper.toDto(transacsiyaRepo.findAll()));
    }

    public ResponseDto deleteTransacsiya(Integer id) {
        Optional<Transacsiya> transacsiya = transacsiyaRepo.findById(id);
        if (transacsiya.isPresent()) {
            transacsiyaRepo.delete(transacsiya.get());
            return ResponseDto.getSuccess(null);
        } else {
            return ResponseDto.UserNotFound();
        }
    }

    public ResponseDto evaluateTransaction(String transaction_uuid, Integer score) {
        Optional<Transacsiya> transacsiya = transacsiyaRepo.findByTransacsiyaUuid(transaction_uuid);
        if (transacsiya.isPresent()) {
            if (score >= 1 && score <= 10) {
                transacsiya.get().setScore(score);
                transacsiyaRepo.save(transacsiya.get());
                return ResponseDto.getSuccess(true);
            } else {
                transacsiya.get().setScore(score);
                return ResponseDto.getSuccess(false);
            }
        } else {
            return ResponseDto.UserNotFound();
        }
    }

    public ResponseDto deliveryPerRegions(String region_name){
        return ResponseDto.getSuccess(transacsiyaRepo.countByCarrier_RegionsName(region_name));
    }

    public ResponseDto scorePerCarrier(Integer score){
        return ResponseDto.getSuccess(transacsiyaRepo.scorePerCarrier(score));
    }

    public ResponseDto transactionPerProduct(){
        return ResponseDto.getSuccess(transacsiyaRepo.transactionPerProduct());
    }
}
