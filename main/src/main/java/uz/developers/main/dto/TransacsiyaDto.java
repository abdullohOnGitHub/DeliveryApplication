package uz.developers.main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransacsiyaDto {
    private Integer id;
    private String transacsiya_uuid;
    private String carrier_name;
    private String request_uuid;
    private String offer_uuid;
    private Integer score;
}
