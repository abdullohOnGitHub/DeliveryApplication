package uz.developers.main.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.developers.main.dto.RequestDistrictDto;
import uz.developers.main.dto.ResponseDto;
import uz.developers.main.mapper.DistrictMapper;
import uz.developers.main.repo.DistrictRepo;

@Service
@RequiredArgsConstructor
public class DistrictService {
    private final DistrictRepo districtRepo;

    private final DistrictMapper districtMapper;

    public ResponseDto getAllDistrict() throws Exception {
        return ResponseDto.getSuccess(districtMapper.toDto(districtRepo.findAll()));
    }

    public ResponseDto getDistrictByRegionName(String region_name) throws Exception {
        return ResponseDto.getSuccess(districtMapper.toDto(districtRepo.findByRegionName(region_name)));
    }


    public ResponseDto addDistricts(RequestDistrictDto requestDistrictDto) throws Exception {
        return ResponseDto.getSuccess(districtMapper.toDto(districtRepo.save(districtMapper.toEntity(requestDistrictDto))));
    }

    public ResponseDto deleteDistricts(Integer id) {
        if (districtRepo.existsById(id)) {
            districtRepo.deleteById(id);
            return ResponseDto.getSuccess(null);
        } else {
            return ResponseDto.UserNotFound();
        }
    }
}
