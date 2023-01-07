package uz.developers.main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import uz.developers.main.annotation.ValidatePhoneNumber;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestCarrierDto {
    private Integer id;
    @NotBlank(message = "Firstname is not valid")
    private String first_name;
    @NotBlank(message = "Lastname is not valid")
    private String last_name;
    @ValidatePhoneNumber(message = "PhoneNumber is not valid") // it should be as 997777777
    private String phone_number;
    @Email(message = "Email is not valid")
    private String email;
    @Length(min = 8, message = "Password length is not enough")
    private String password;
    @NotNull
    private Integer regionId;
}
