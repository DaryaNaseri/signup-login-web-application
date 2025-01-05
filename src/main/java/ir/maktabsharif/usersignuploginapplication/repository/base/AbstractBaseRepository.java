package ir.maktabsharif.usersignuploginapplication.repository.base;

import ir.maktabsharif.usersignuploginapplication.model.base.BaseEntity;
import ir.maktabsharif.usersignuploginapplication.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

public abstract class AbstractBaseRepository<TYPE extends BaseEntity<ID>, ID extends Serializable> implements BaseRepository<TYPE,ID> {
private EntityManagerFactory emf= JPAUtil.getEmf();

    private final Class<TYPE> entityClass;

    @SuppressWarnings("unchecked")
    public AbstractBaseRepository() {
        entityClass = (Class<TYPE>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

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
            TYPE type = entityManager.find(entityClass, id);
            entityManager.getTransaction().commit();
            return Optional.ofNullable(type);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<TYPE> findAll() {
        EntityManager entityManager = emf.createEntityManager();
        return entityManager
                .createQuery("from " + entityClass.getSimpleName(), entityClass)
                .getResultList();
    }


}
