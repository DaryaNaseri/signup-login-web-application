package ir.maktabsharif.usersignuploginapplication.model.dto;


import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
@Builder
public class SignupRequestDto {

    //todo:check space
    @Size(min = 5, message = "username must be between five to ten characters")
    private String username;

    @Size(min = 8 ,message = "password must have at least eight characters")
    private String password;



}
