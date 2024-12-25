package ir.maktabsharif.usersignuploginapplication.repository;

import ir.maktabsharif.usersignuploginapplication.model.User;
import ir.maktabsharif.usersignuploginapplication.repository.base.AbstractBaseRepository;

public class UserRepositoryImpl extends AbstractBaseRepository<User, Long> implements UserRepository {

    public Class<User> getClassType() {
        return User.class;
    }

}
