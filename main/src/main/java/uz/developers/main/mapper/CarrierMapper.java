package uz.developers.main.mapper;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.developers.main.dto.RequestCarrierDto;
import uz.developers.main.dto.ResponseCarrierDto;
import uz.developers.main.entity.Carrier;
import uz.developers.main.entity.District;
import uz.developers.main.entity.Region;
import uz.developers.main.repo.DistrictRepo;
import uz.developers.main.repo.RegionRepo;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CarrierMapper {
    private final RegionRepo regionRepo;
    private final DistrictRepo districtRepo;

    private final DistrictMapper districtMapper;

    public Carrier toEntity(RequestCarrierDto requestCarrierDto) throws Exception {
        Optional<Region> region = regionRepo.findById(requestCarrierDto.getRegionId());
        if (region.isPresent()) {
            return new Carrier(
                    requestCarrierDto.getId(),
                    requestCarrierDto.getFirst_name(),
                    requestCarrierDto.getLast_name(),
                    requestCarrierDto.getPhone_number(),
                    requestCarrierDto.getEmail(),
                    requestCarrierDto.getPassword(),
                    true,
                    region.get()
            );
        } else {
            throw new Exception("Region not found");
        }
    }

    public RequestCarrierDto toRequestDto(Carrier carrier) {
        return new RequestCarrierDto(
                carrier.getId(),
                carrier.getFirstName(),
                carrier.getLastName(),
                carrier.getPhoneNumber(),
                carrier.getEmail(),
                carrier.getPassword(),
                carrier.getRegions().getId()
        );
    }

    public ResponseCarrierDto toResponseDto(Carrier carrier) {
        List<District> districts = districtRepo.findByRegionId(carrier.getRegions().getId());

        return new ResponseCarrierDto(
                carrier.getId(),
                carrier.getFirstName(),
                carrier.getLastName(),
                carrier.getPhoneNumber(),
                carrier.getEmail(),
                carrier.getPassword(),
                carrier.getRegions().getName(),
                districtMapper.toDto(districts)
        );
    }

    public List<ResponseCarrierDto> toResponseDto(List<Carrier> carriers) {
        List<ResponseCarrierDto> responseCarrierDtos = new LinkedList<>();
        for (Carrier carrier : carriers) {
            responseCarrierDtos.add(toResponseDto(carrier));
        }
        return responseCarrierDtos;
    }


}
