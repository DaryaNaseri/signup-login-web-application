package ir.maktabsharif.usersignuploginapplication.listener;

import com.github.javafaker.Faker;
import ir.maktabsharif.usersignuploginapplication.model.entity.*;
import ir.maktabsharif.usersignuploginapplication.repository.UserRepository;
import ir.maktabsharif.usersignuploginapplication.repository.UserRepositoryImpl;
import ir.maktabsharif.usersignuploginapplication.security.BCryptPasswordEncode;
import ir.maktabsharif.usersignuploginapplication.util.JPAUtil;
import ir.maktabsharif.usersignuploginapplication.util.ReadXml;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Random;

@WebListener
public class PermissionServletContextListener implements ServletContextListener {
    private UserRepository userRepository = new UserRepositoryImpl();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        securityDataInitializer();
        postsDataInitializer();
        userDataInitializer();
    }
    private void securityDataInitializer(){
        ReadXml.readXml();

        EntityManagerFactory entityManagerFactory = JPAUtil.getEmf();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        for (Permission permissionItem : ReadXml.getPermissions()){
            entityManager.persist(permissionItem);
        }

        for (UserRole roleItem : ReadXml.getGlobalRoles()){
            entityManager.persist(roleItem);
        }
        transaction.commit();
    }
    private void postsDataInitializer(){

        Faker faker = new Faker();

        for (int i = 0; i < 10; i++) {
            EntityManagerFactory entityManagerFactory = JPAUtil.getEmf();
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();

            Post post = Post.builder()
                    .postStatus(PostStatus.PUBLIC)
                    .title(faker.lorem().sentence(3))
                    .content(faker.lorem().sentence(10))
                    .build();
            entityManager.persist(post);
            transaction.commit();
        }
    }
    private void userDataInitializer(){
        Faker faker = new Faker();

        for (int i = 0; i < 3; i++) {
            EntityManagerFactory entityManagerFactory = JPAUtil.getEmf();
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();

            User user = User.builder()
                    .username(faker.name().username())
                    .password(BCryptPasswordEncode.encodeBCryptPassword("1234567"))
                    .fullName(faker.name().fullName())
                    .email(faker.internet().emailAddress())
                    .phone(faker.phoneNumber().phoneNumber())
                    .age(String.valueOf(new Random().nextInt(13) + 18))
                    .userRole(userRepository.findUserRoleByUserRoleName("USER"))
                    .build();
            entityManager.persist(user);
            transaction.commit();
        }
    }
}
