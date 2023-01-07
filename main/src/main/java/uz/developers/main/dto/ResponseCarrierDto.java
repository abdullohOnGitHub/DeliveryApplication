package uz.developers.main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.developers.main.entity.District;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCarrierDto {
    private Integer id;
    private String first_name;
    private String last_name;
    private String phone_number;
    private String email;
    private String password;
    private String regionName;
    private List<ResponseDistrictDto> districts;

}
