package ir.maktabsharif.usersignuploginapplication.repository;

import ir.maktabsharif.usersignuploginapplication.model.User;
import ir.maktabsharif.usersignuploginapplication.model.UserRole;
import ir.maktabsharif.usersignuploginapplication.repository.base.AbstractBaseRepository;
import ir.maktabsharif.usersignuploginapplication.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserRepositoryImpl extends AbstractBaseRepository<User, Long> implements UserRepository {

    private final EntityManagerFactory emf= JPAUtil.getEmf();

    public List<UserRole> findUserRoleByUserRoleName(String roleName) {
        EntityManager entityManager = emf.createEntityManager();

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<UserRole> query = cb.createQuery(UserRole.class);

        Root<UserRole> userRoleRoot = query.from(UserRole.class);

        Predicate userRolePredicate = cb.equal(userRoleRoot.get("roleName"), roleName);

        return entityManager
                .createQuery(query.select(userRoleRoot).where(cb.and(userRolePredicate)))
                .getResultList();

    }
}
