package uz.developers.main.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.developers.main.dto.RequestDto;
import uz.developers.main.dto.ResponseDto;
import uz.developers.main.service.RequestService;

import javax.validation.Valid;

@RestController
@RequestMapping("/main/request")
@RequiredArgsConstructor
public class RequestController {
    private final RequestService requestService;

    @PostMapping("/add")
    public ResponseDto addRequest(@RequestBody @Valid RequestDto requestDto) throws Exception {
        return requestService.addRequest(requestDto);
    }

    @GetMapping("/all")
    public ResponseDto getAll(){
        return requestService.getAllRequest();
    }

    @DeleteMapping
    public ResponseDto deleteRequest(@RequestParam Integer id){
        return  requestService.deleteRequest(id);
    }
}
