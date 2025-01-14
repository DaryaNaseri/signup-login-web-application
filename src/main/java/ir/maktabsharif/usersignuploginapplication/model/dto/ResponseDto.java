package ir.maktabsharif.usersignuploginapplication.model.dto;


import ir.maktabsharif.usersignuploginapplication.model.entity.UserRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDto {

    private Long id;

    private String email;

    private String fullName;

    private String phone;

    private String username;

    private String password;

    private String age;

    private String photoHash;

    private UserRole userRole;

}
