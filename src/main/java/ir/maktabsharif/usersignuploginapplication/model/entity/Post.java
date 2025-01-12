package ir.maktabsharif.usersignuploginapplication.model.entity;

import ir.maktabsharif.usersignuploginapplication.model.base.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Post extends BaseEntity<Long> {
    private String title;
    private String content;

    @ManyToOne
    @JoinColumn(name = "fk_user")
    private User user;
}
