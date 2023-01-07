package uz.developers.main.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.developers.main.dto.RequestDistrictDto;
import uz.developers.main.dto.ResponseDistrictDto;
import uz.developers.main.entity.District;
import uz.developers.main.entity.Region;
import uz.developers.main.repo.RegionRepo;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DistrictMapper {

    private final RegionRepo regionRepo;

    public District toEntity(RequestDistrictDto requestDistrictDto) throws Exception {
        Optional<Region> region = regionRepo.findByName(requestDistrictDto.getRegionName());
        if (region.isPresent()) {
            return new District(requestDistrictDto.getId(), requestDistrictDto.getName(), region.get());
        } else {
            throw new Exception("Region not found");
        }
    }


    public ResponseDistrictDto toDto(District district) {
        return new ResponseDistrictDto(district.getId(), district.getName());
    }

    public List<ResponseDistrictDto> toDto(List<District> districts) {
        List<ResponseDistrictDto> districtDto = null;
        if (districts != null) {
            boolean exists = regionRepo.existsByName(districts.get(0).getRegion().getName());
            if (exists) {
                districtDto = new LinkedList<>();
                for (District district : districts) {
                    districtDto.add(new ResponseDistrictDto(district.getId(), district.getName()));
                }
            }
            return districtDto;
        } else {
            try {
                throw new Exception("Region not found");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
