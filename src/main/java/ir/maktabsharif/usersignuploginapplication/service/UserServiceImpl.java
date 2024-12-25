package ir.maktabsharif.usersignuploginapplication.service;

import ir.maktabsharif.usersignuploginapplication.model.dto.UserRequestDto;
import ir.maktabsharif.usersignuploginapplication.model.User;
import ir.maktabsharif.usersignuploginapplication.repository.UserRepository;
import ir.maktabsharif.usersignuploginapplication.repository.UserRepositoryImpl;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    private UserRepository userRepository =new UserRepositoryImpl();


    @Override
    public Boolean save(UserRequestDto entity) {
        User user = new User(entity.getUsername(), entity.getPassword());
        Optional<Long> createdUserId = userRepository.save(user);
        return createdUserId.isPresent() && createdUserId.get() > 0;
    }

    @Override
    public User findById(Long id) {
        Optional<User> findById = userRepository.findById(id);
        return findById.orElse(null);
        }
}
