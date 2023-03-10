package uz.developers.main.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.developers.main.dto.RequestDistrictDto;
import uz.developers.main.dto.ResponseDto;
import uz.developers.main.service.DistrictService;

import javax.validation.Valid;

@RestController
@RequestMapping("/main/district")
@RequiredArgsConstructor
public class DistrictController {

    private final DistrictService districtService;


    @GetMapping("/all")
    public ResponseDto getAllDistricts() throws Exception {
        return districtService.getAllDistrict();
    }

    @GetMapping("/all/region")
    public ResponseDto getAllDistrictsByRegionName(@RequestParam String name) throws Exception {
        return districtService.getDistrictByRegionName(name);
    }

    @PostMapping("/add")
    public ResponseDto addDistrict(@RequestBody @Valid RequestDistrictDto requestDistrictDto) throws Exception {
        return districtService.addDistricts(requestDistrictDto);
    }

    @DeleteMapping()
    public ResponseDto deleteDistrict(@RequestParam Integer id) {
        return districtService.deleteDistricts(id);
    }
}
