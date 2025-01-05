package ir.maktabsharif.usersignuploginapplication.model;

import ir.maktabsharif.usersignuploginapplication.model.base.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
@Data
@Table(name = "users")
public class User extends BaseEntity<Long> {

    private String username;

    private String password;

    @ManyToMany
    @JoinTable(
            name = "j_user_permission",
            joinColumns = {@JoinColumn(name = "fk_user")},
            inverseJoinColumns = {@JoinColumn(name = "fk_permission")}
    )
    private List<Permission> permissions;

    @ManyToOne
    @JoinColumn(name = "fk_userRole")
    private UserRole userRole;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;


    public User( String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User( String username,  String password, UserRole userRole) {
        this.username = username;
        this.password = password;
        this.userRole = userRole;
    }
}
