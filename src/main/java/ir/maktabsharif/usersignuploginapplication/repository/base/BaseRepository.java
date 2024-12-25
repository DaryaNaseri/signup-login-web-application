package ir.maktabsharif.usersignuploginapplication.repository.base;

import ir.maktabsharif.usersignuploginapplication.model.base.BaseEntity;

import java.io.Serializable;
import java.util.Optional;

public interface BaseRepository<TYPE extends BaseEntity<ID>, ID extends Serializable> {

    Optional<ID> save(TYPE entity);

    Optional<TYPE> findById(ID id);

}
