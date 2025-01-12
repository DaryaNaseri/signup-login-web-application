package ir.maktabsharif.usersignuploginapplication.repository;

import ir.maktabsharif.usersignuploginapplication.model.entity.User;
import ir.maktabsharif.usersignuploginapplication.model.entity.UserRole;
import ir.maktabsharif.usersignuploginapplication.repository.base.BaseRepository;

import java.util.List;

public interface UserRepository extends BaseRepository<User,Long> {
    UserRole findUserRoleByUserRoleName(String roleName);
    Boolean userNameExists(String userName);
}
