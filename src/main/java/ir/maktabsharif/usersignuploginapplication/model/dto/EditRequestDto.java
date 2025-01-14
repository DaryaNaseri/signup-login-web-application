package ir.maktabsharif.usersignuploginapplication.model.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
@Data
@Builder
public class EditRequestDto {

    private Long id;

    private String username;

    private String password;

    @Email(message = "enter email in this format: example@gmail.com ")
    private String email;

    @Pattern(regexp = "^(\\+98|0)?9\\d{9}$", message = "please enter valid phone number")
    private String phone;

    @NotBlank
    private String fullName;

    @NotBlank
    private String age;

    private String photoHash;

}
