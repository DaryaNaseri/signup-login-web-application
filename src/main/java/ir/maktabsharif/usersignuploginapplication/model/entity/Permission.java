package ir.maktabsharif.usersignuploginapplication.model.entity;


import ir.maktabsharif.usersignuploginapplication.model.base.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor

public class Permission extends BaseEntity<Long> {

    private String permissionName;

}
