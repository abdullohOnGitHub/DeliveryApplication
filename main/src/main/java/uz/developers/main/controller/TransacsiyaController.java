package uz.developers.main.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.developers.main.dto.ResponseDto;
import uz.developers.main.dto.TransacsiyaDto;
import uz.developers.main.service.TransacsiyaService;

import javax.validation.Valid;

@RestController
@RequestMapping("/main/transaction")
@RequiredArgsConstructor
public class TransacsiyaController {
    private final TransacsiyaService transacsiyaService;

    @PostMapping("/add")
    public ResponseDto addTransaction(@RequestBody @Valid TransacsiyaDto transacsiyaDto) throws Exception {
        return transacsiyaService.addTransacsiya(transacsiyaDto);
    }

    @PostMapping("/evaluate")
    public ResponseDto evaluateTransaction(@RequestParam String transaction_uuid, Integer score) {
        return transacsiyaService.evaluateTransaction(transaction_uuid, score);
    }

    @GetMapping("/all")
    public ResponseDto getAll() {
        return transacsiyaService.getAll();
    }


    @DeleteMapping
    public ResponseDto deleteTransaction(@RequestParam Integer id) {
        return transacsiyaService.deleteTransacsiya(id);
    }
}
