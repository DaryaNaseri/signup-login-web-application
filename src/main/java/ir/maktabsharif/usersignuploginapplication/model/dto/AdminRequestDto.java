package ir.maktabsharif.usersignuploginapplication.model.dto;

import ir.maktabsharif.usersignuploginapplication.model.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminRequestDto {
    private String username;
    private String password;
    private UserRole userRole;

    public AdminRequestDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
