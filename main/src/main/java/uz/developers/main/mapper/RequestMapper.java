package uz.developers.main.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.developers.main.dto.RequestDto;
import uz.developers.main.entity.District;
import uz.developers.main.entity.Request;
import uz.developers.main.repo.DistrictRepo;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RequestMapper {
    private final DistrictRepo districtRepo;

    public Request toEntity(RequestDto requestDto) throws Exception {
        Optional<District> district = districtRepo.findByName(requestDto.getDistrict_name());
        if (district.isPresent()) {
            return new Request(requestDto.getId(), requestDto.getRequest_uuid(), district.get(), requestDto.getProduct_uuid());
        } else {
            throw new Exception("District is not present");
        }
    }

    public RequestDto toDto(Request request) {
        return new RequestDto(request.getId(), request.getRequestUuid(), request.getDistrict().getName(), request.getProductUuid());
    }

    public List<RequestDto> toDto(List<Request> requests) {
        List<RequestDto> requestDtos = null;
        if (requests != null) {
            for (Request request : requests) {
                requestDtos.add(toDto(request));
            }
        }
        return requestDtos;
    }
}
