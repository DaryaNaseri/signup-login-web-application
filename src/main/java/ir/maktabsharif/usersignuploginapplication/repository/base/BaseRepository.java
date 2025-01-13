package ir.maktabsharif.usersignuploginapplication.repository.base;

import ir.maktabsharif.usersignuploginapplication.model.base.BaseEntity;
import ir.maktabsharif.usersignuploginapplication.model.entity.User;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface BaseRepository<TYPE extends BaseEntity<ID>, ID extends Serializable> {

    Optional<ID> save(TYPE entity);

    Optional<TYPE> findById(ID id);

    List<TYPE> findAll();

    Optional<TYPE> update(TYPE entity);
}
