package ir.maktabsharif.usersignuploginapplication.model.entity;

import ir.maktabsharif.usersignuploginapplication.model.base.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
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

    private String email;

    private String phone;

    private String fullName;

    private String age;

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


}
