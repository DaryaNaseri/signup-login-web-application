package ir.maktabsharif.usersignuploginapplication.service;

import ir.maktabsharif.usersignuploginapplication.model.dto.UserRequestDto;
import ir.maktabsharif.usersignuploginapplication.model.User;
import ir.maktabsharif.usersignuploginapplication.model.dto.UserResponseDto;
import ir.maktabsharif.usersignuploginapplication.repository.UserRepository;
import ir.maktabsharif.usersignuploginapplication.repository.UserRepositoryImpl;
import ir.maktabsharif.usersignuploginapplication.service.base.AbstractBaseService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserServiceImpl extends AbstractBaseService<UserRequestDto,User,Long> implements UserService {
    private UserRepository userRepository =new UserRepositoryImpl();


    @Override
    public Boolean save(UserRequestDto entity) {
        Optional<Long> createdUserId = Optional.empty();

        if (!this.findAllUsers().isEmpty()) {
            for (UserResponseDto item: this.findAllUsers()){
                if (item.getUsername().equals(entity.getUsername())){
                    return false;
                }else {
                    User user = new User(entity.getUsername(), entity.getPassword());
                    createdUserId = userRepository.save(user);}
                }
        }else {
            User user = new User(entity.getUsername(), entity.getPassword());
            createdUserId = userRepository.save(user);
        }

        return createdUserId.isPresent() && createdUserId.get() > 0;
    }

    @Override
    public User findById(Long id) {
        Optional<User> findById = userRepository.findById(id);
        return findById.orElse(null);
        }

    @Override
    public List<UserResponseDto> findAllUsers() {
        List<User> allUsers = userRepository.findAll();
        return allUsers.stream()
                .map(user-> UserResponseDto.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public Boolean findByUserName(UserRequestDto userRequestDto) {
        for (UserResponseDto item: this.findAllUsers()){
            if (item.getUsername().equals(userRequestDto.getUsername())){
                if (item.getPassword().equals(userRequestDto.getPassword())){
                    return true;
                }
            }
        }
        return false;
    }


}
