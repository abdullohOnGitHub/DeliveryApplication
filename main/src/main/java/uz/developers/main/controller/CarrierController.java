package uz.developers.main.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.developers.main.dto.RequestCarrierDto;
import uz.developers.main.dto.ResponseDto;
import uz.developers.main.service.CarrierService;

import javax.validation.Valid;

@RestController
@RequestMapping("/main/carrier")
@RequiredArgsConstructor
public class CarrierController {
    private final CarrierService carrierService;

    @PostMapping("/add")
    public ResponseDto addCarrier(@RequestBody @Valid RequestCarrierDto requestCarrierDto) {
        try {
            return carrierService.addCarrier(requestCarrierDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseDto(-1, "Region not valid", null);
    }

    @GetMapping("/region")
    public ResponseDto getCarriersForRegion(@RequestParam String name) {
        return carrierService.getCarriersByRegionName(name);
    }

    @GetMapping("/all")
    public ResponseDto getAllCarrier() {
        return carrierService.getAllCarrier();
    }

    @GetMapping("/{id}")
    public ResponseDto getCarrierById(@PathVariable Integer id) {
        return carrierService.getCarrierById(id);
    }

    @PutMapping("/update")
    public ResponseDto updateCarrier(@RequestBody @Valid RequestCarrierDto requestCarrierDto) throws Exception {
        return carrierService.updateCarrier(requestCarrierDto);
    }

    @DeleteMapping("/{id}")
    public ResponseDto deleteCarrier(@PathVariable Integer id) {
        return carrierService.deleteCarrier(id);
    }
}
