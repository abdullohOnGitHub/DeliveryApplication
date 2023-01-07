package uz.developers.main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDistrictDto {
    private Integer id;
    @NotBlank(message = "Name is not valid")
    private String name;
    @NotNull
    private String regionName;
}
