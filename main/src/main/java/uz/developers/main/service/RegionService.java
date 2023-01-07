package uz.developers.main.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.developers.main.dto.RegionDto;
import uz.developers.main.dto.ResponseDto;
import uz.developers.main.entity.Region;
import uz.developers.main.mapper.RegionMapper;
import uz.developers.main.repo.RegionRepo;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegionService {

    private final RegionRepo regionRepository;

    public ResponseDto addRegion(RegionDto regionDto) {
        boolean exists = regionRepository.existsByName(regionDto.getName());
        if (exists) {
            return new ResponseDto(-1, "Region already created", null);
        }
        Region region = regionRepository.save(RegionMapper.toEntity(regionDto));
        return ResponseDto.getSuccess(region);
    }

    public ResponseDto getAll() {
        return ResponseDto.getSuccess(regionRepository.findAll());
    }

    public ResponseDto getRegionById(Integer id) {
        if (regionRepository.findById(id).isPresent()) {
            return ResponseDto.getSuccess(regionRepository.findById(id).get());
        }
        return ResponseDto.UserNotFound();
    }

    public ResponseDto updateRegion(RegionDto regionDto) {
        Optional<Region> region = regionRepository.findByName(regionDto.getName());
        if (region.isPresent()) {
            return ResponseDto.getSuccess(RegionMapper.toDto(regionRepository.save(RegionMapper.toEntity(regionDto))));
        } else {
            return ResponseDto.UserNotFound();
        }
    }

    public ResponseDto deleteRegion(Integer id) {
        Optional<Region> region = regionRepository.findById(id);
        if (region.isPresent()) {
            regionRepository.delete(region.get());
            return ResponseDto.getSuccess(null);
        } else {
            return ResponseDto.UserNotFound();
        }
    }
}
