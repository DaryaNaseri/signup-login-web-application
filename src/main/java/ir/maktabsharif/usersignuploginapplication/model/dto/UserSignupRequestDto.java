package ir.maktabsharif.usersignuploginapplication.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSignupRequestDto {

    @NotBlank(message = "username is required!")
    @Size(min = 6, message = "invalid username ,must be at leastt 6 characters!")
    private String username;

    @NotBlank(message = "password is required!")
    @Size(min = 8, message = "invalid password it should be at least eight character!")
    private String password;

}
