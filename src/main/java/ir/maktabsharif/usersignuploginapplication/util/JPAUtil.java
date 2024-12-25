package ir.maktabsharif.usersignuploginapplication.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class JPAUtil {
    private static EntityManagerFactory emf;

    private JPAUtil() {
    }

    public static EntityManagerFactory getEmf() {
        if (emf == null)
            emf = Persistence.createEntityManagerFactory("jdbc-postgres");
        return emf;
    }
}
