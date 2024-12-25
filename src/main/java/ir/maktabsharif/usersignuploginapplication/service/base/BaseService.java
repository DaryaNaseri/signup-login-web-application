package ir.maktabsharif.usersignuploginapplication.service.base;

import ir.maktabsharif.usersignuploginapplication.model.base.BaseEntity;

import java.io.Serializable;

public interface BaseService<DTO,TYPE extends BaseEntity<ID>, ID extends Serializable> {
    Boolean save(DTO entity);

    TYPE findById(ID id);
}
