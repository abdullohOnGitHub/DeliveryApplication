package uz.developers.main.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferDto {
    private Integer id;
    @NotNull(message = "offer_uuid is not valid")
    private String offer_uuid;
    @NotNull(message = "District is not valid")
    private String district_name;
    @NotNull(message = "product_uuid is not valid")
    private String product_uuid;
}
