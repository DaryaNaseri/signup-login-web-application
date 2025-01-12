package ir.maktabsharif.usersignuploginapplication.repository;

import ir.maktabsharif.usersignuploginapplication.model.entity.User;
import ir.maktabsharif.usersignuploginapplication.model.entity.UserRole;
import ir.maktabsharif.usersignuploginapplication.model.queryResult.ExistUserName;
import ir.maktabsharif.usersignuploginapplication.repository.base.AbstractBaseRepository;
import ir.maktabsharif.usersignuploginapplication.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserRepositoryImpl extends AbstractBaseRepository<User, Long> implements UserRepository {

    private final EntityManagerFactory emf= JPAUtil.getEmf();

    public UserRole findUserRoleByUserRoleName(String roleName) {
        EntityManager entityManager = emf.createEntityManager();

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<UserRole> query = cb.createQuery(UserRole.class);

        Root<UserRole> userRoleRoot = query.from(UserRole.class);

        Predicate userRolePredicate = cb.equal(userRoleRoot.get("roleName"), roleName);

        return entityManager
                .createQuery(query.select(userRoleRoot).where(cb.and(userRolePredicate)))
                .getResultList().get(0);

    }


    public Boolean userNameExists(String userName) {
        EntityManager entityManager = emf.createEntityManager();
        TypedQuery<ExistUserName> query = entityManager.createQuery(
                "select new ir.maktabsharif.usersignuploginapplication.model.queryResult.ExistUserName ( " +
                        "case when count (ua)<>0 then true " +
                        "else false " +
                        "end " +
                        ") From User ua where ua.username =: username ", ExistUserName.class);
        query.setParameter("username", userName);
        return query.getSingleResult().getIsExist();

    }
}
