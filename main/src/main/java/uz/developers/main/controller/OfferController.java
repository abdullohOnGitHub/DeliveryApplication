package uz.developers.main.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.developers.main.dto.OfferDto;
import uz.developers.main.dto.RequestDto;
import uz.developers.main.dto.ResponseDto;
import uz.developers.main.service.OfferService;
import uz.developers.main.service.RequestService;

import javax.validation.Valid;

@RestController
@RequestMapping("/main/offer")
@RequiredArgsConstructor
public class OfferController {

    private final OfferService offerService;

    @PostMapping("/add")
    public ResponseDto addRequest(@RequestBody @Valid OfferDto offerDto) throws Exception {
        return offerService.addRequest(offerDto);
    }

    @GetMapping("/all")
    public ResponseDto getAll(){
        return offerService.getAllRequest();
    }

    @DeleteMapping
    public ResponseDto deleteRequest(@RequestParam Integer id){
        return  offerService.deleteRequest(id);
    }


}
