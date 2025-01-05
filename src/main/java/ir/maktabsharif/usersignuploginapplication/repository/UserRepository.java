package ir.maktabsharif.usersignuploginapplication.repository;

import ir.maktabsharif.usersignuploginapplication.model.User;
import ir.maktabsharif.usersignuploginapplication.model.UserRole;
import ir.maktabsharif.usersignuploginapplication.repository.base.BaseRepository;

import java.util.List;

public interface UserRepository extends BaseRepository<User,Long> {
    List<UserRole> findUserRoleByUserRoleName(String rolename);

}
