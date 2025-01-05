package ir.maktabsharif.usersignuploginapplication.model;

import ir.maktabsharif.usersignuploginapplication.model.base.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class UserRole extends BaseEntity<Long> {

    private String roleName;

    @ManyToMany
    @JoinTable(
            name = "j_user_role_permission",
            joinColumns = {@JoinColumn(name = "fk_user_role")},
            inverseJoinColumns = {@JoinColumn(name = "fk_permission")}
    )
    private List<Permission> permissions;


    public UserRole(String roleName) {
        this.roleName = roleName;
    }

    public static Class<UserRole> getClazz() {
        return UserRole.class;
    }
}
