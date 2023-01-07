package uz.developers.main.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.developers.main.dto.RequestCarrierDto;
import uz.developers.main.dto.ResponseCarrierDto;
import uz.developers.main.dto.ResponseDto;
import uz.developers.main.entity.Carrier;
import uz.developers.main.mapper.CarrierMapper;
import uz.developers.main.repo.CarrierRepo;
import uz.developers.main.repo.RegionRepo;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarrierService {
    private final CarrierRepo carrierRepo;
    private final RegionRepo regionRepo;
    private final CarrierMapper carrierMapper;

    public ResponseDto addCarrier(RequestCarrierDto requestCarrierDto) throws Exception {
        ResponseCarrierDto carrierDto = carrierMapper.toResponseDto(carrierRepo.save(carrierMapper.toEntity(requestCarrierDto)));
        return ResponseDto.getSuccess(carrierDto);
    }

    public ResponseDto getCarriersByRegionName(String region_name) {
        List<Carrier> carriers = carrierRepo.findByRegionsName(region_name);
        return ResponseDto.getSuccess(carrierMapper.toResponseDto(carriers));
    }

    public ResponseDto getAllCarrier() {
        List<Carrier> carrierList = carrierRepo.findAll();
        return ResponseDto.getSuccess(carrierMapper.toResponseDto(carrierList));
    }

    public ResponseDto getCarrierById(Integer id) {
        Optional<Carrier> carrier = carrierRepo.findById(id);
        return carrier.map(value -> ResponseDto.getSuccess(carrierMapper.toResponseDto(value))).orElseGet(ResponseDto::UserNotFound);
    }

    public ResponseDto updateCarrier(RequestCarrierDto requestCarrierDto) {
        Optional<Carrier> carrier = carrierRepo.findById(requestCarrierDto.getId());
        if (carrier.isPresent()) {
            return ResponseDto.getSuccess(carrierMapper.toResponseDto(carrierRepo.save(carrier.get())));
        } else {
            return ResponseDto.UserNotFound();
        }
    }

    public ResponseDto deleteCarrier(Integer id) {
        Optional<Carrier> carrier = carrierRepo.findById(id);
        if (carrier.isPresent()) {
            carrier.get().setIsActive(false);
            carrierRepo.save(carrier.get());
            return new ResponseDto(0, "Carrier deleted", null);
        } else {
            return ResponseDto.UserNotFound();
        }
    }
}
