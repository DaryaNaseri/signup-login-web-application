package ir.maktabsharif.usersignuploginapplication.model;

import ir.maktabsharif.usersignuploginapplication.model.base.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "users")
public class User extends BaseEntity<Long> {

    private String username;

    private String password;
}
