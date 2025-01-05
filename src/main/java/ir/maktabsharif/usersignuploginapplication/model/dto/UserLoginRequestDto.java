package ir.maktabsharif.usersignuploginapplication.model.dto;


import ir.maktabsharif.usersignuploginapplication.model.UserRole;
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
public class UserLoginRequestDto {

    @NotBlank(message = "username is required!")
    @Size(min = 6, max = 15, message = "invalid username!")
    private String username;

    @NotBlank(message = "password is required!")
    @Size(min = 8, message = "invalid password it should be at least eight character!")
    private String password;
}
