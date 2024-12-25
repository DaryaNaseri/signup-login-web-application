package ir.maktabsharif.usersignuploginapplication.repository.base;

import ir.maktabsharif.usersignuploginapplication.model.base.BaseEntity;
import ir.maktabsharif.usersignuploginapplication.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.Serializable;
import java.util.Optional;

public abstract class AbstractBaseRepository<TYPE extends BaseEntity<ID>, ID extends Serializable> implements BaseRepository<TYPE,ID> {
private EntityManagerFactory emf= JPAUtil.getEmf();

    @Override
    public Optional<ID> save(TYPE entity) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        return Optional.of(entity.getId());
    }

    @Override
    public Optional<TYPE> findById(ID id) {
        EntityManager entityManager = emf.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            TYPE type = entityManager.find(getClassType(), id);
            entityManager.getTransaction().commit();
            return Optional.ofNullable(type);
        } finally {
            entityManager.close();
        }
    }


    public abstract Class<TYPE> getClassType();
}
