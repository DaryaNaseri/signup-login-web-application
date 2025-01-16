package ir.maktabsharif.usersignuploginapplication.repository;

import ir.maktabsharif.usersignuploginapplication.model.entity.Permission;
import ir.maktabsharif.usersignuploginapplication.model.entity.UserRole;
import ir.maktabsharif.usersignuploginapplication.util.JPAUtil;
import ir.maktabsharif.usersignuploginapplication.util.ReadXml;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

public class UserRolePermissionRepository {

        private static EntityManagerFactory emf= JPAUtil.getEmf();
        private static EntityManager em=emf.createEntityManager();
    private static EntityTransaction transaction = em.getTransaction();

        public static void createUserRoleAndPermission(){
            ReadXml.readXml();

            transaction.begin();

            for (Permission permissionItem : ReadXml.getPermissions()){
                em.persist(permissionItem);
            }

            for (UserRole roleItem : ReadXml.getGlobalRoles()){
                em.persist(roleItem);
            }

            transaction.commit();
        }



}
