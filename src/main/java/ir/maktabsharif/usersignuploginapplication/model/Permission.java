package ir.maktabsharif.usersignuploginapplication.model;


import ir.maktabsharif.usersignuploginapplication.model.base.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor

public class Permission extends BaseEntity<Long> {

    private String permissionName;

}
