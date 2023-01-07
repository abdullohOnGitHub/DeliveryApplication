package uz.developers.main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegionDto {
    private Integer id;
    @NotBlank(message = "Name is not valid")
    private String name;
}
