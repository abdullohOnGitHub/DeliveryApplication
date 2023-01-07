package uz.developers.main.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.developers.main.dto.RequestDto;
import uz.developers.main.dto.ResponseDto;
import uz.developers.main.entity.Request;
import uz.developers.main.mapper.RequestMapper;
import uz.developers.main.repo.RequestRepo;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RequestService {
    private final RequestRepo requestRepo;
    private final RequestMapper requestMapper;

    public ResponseDto addRequest(RequestDto requestDto) throws Exception {
        Request request = requestMapper.toEntity(requestDto);
        if (requestRepo.existsByRequestUuid(request.getRequestUuid())) {
            return new ResponseDto(-1, "Request already created", null);
        }
        return ResponseDto.getSuccess(requestMapper.toDto(requestRepo.save(request)));
    }

    public ResponseDto getAllRequest() {
        return ResponseDto.getSuccess(requestMapper.toDto(requestRepo.findAll()));
    }

    public ResponseDto deleteRequest(Integer id) {
        Optional<Request> request = requestRepo.findById(id);
        if (request.isPresent()) {
            requestRepo.delete(request.get());
            return ResponseDto.getSuccess(null);
        } else {
            return ResponseDto.UserNotFound();
        }
    }


}
