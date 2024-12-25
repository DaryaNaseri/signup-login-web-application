package ir.maktabsharif.usersignuploginapplication.service.base;

import ir.maktabsharif.usersignuploginapplication.model.base.BaseEntity;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractBaseService<DTO,TYPE extends BaseEntity<ID>,ID extends Serializable> implements BaseService<DTO,TYPE,ID> {

    @Override
    public abstract Boolean save(DTO entity);

    @Override
    public abstract TYPE findById(ID id) ;

    public List<TYPE> findAll(){return null;}
}
