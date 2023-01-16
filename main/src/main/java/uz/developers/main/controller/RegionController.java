package uz.developers.main.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.developers.main.dto.RegionDto;
import uz.developers.main.dto.ResponseDto;
import uz.developers.main.service.DistrictService;
import uz.developers.main.service.RegionService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/main/region")
public class RegionController {

    private final RegionService regionService;

    @PostMapping("/add")
    public ResponseDto addRegion(@RequestBody @Valid RegionDto regionDto) {
        return regionService.addRegion(regionDto);
    }

    @GetMapping("/all")
    public ResponseDto getAllRegion() {
        return regionService.getAll();
    }

    @GetMapping("{id}")
    public ResponseDto getRegionById(@PathVariable Integer id) {
        return regionService.getRegionById(id);
    }

    @PutMapping("/update")
    public ResponseDto updateRegion(@RequestBody RegionDto regionDto) {
        return regionService.updateRegion(regionDto);
    }

    @DeleteMapping
    public ResponseDto deleteRegion(@RequestParam Integer id) {
        return regionService.deleteRegion(id);
    }


}
