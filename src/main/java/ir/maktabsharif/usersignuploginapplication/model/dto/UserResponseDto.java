package ir.maktabsharif.usersignuploginapplication.model.dto;


import ir.maktabsharif.usersignuploginapplication.model.Permission;
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
public class UserResponseDto {

    private Long id;

    private String username;

    private String password;

    private Permission permission;

}
