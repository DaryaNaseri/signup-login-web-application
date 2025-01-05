package ir.maktabsharif.usersignuploginapplication.service;

import ir.maktabsharif.usersignuploginapplication.model.Permission;
import ir.maktabsharif.usersignuploginapplication.model.UserRole;
import ir.maktabsharif.usersignuploginapplication.model.dto.UserSignupRequestDto;
import ir.maktabsharif.usersignuploginapplication.model.User;
import ir.maktabsharif.usersignuploginapplication.model.dto.UserResponseDto;
import ir.maktabsharif.usersignuploginapplication.repository.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private UserRepository userRepository =new UserRepositoryImpl();

    @Override
    public Boolean save(UserSignupRequestDto entity) {
        Boolean createdUserId = isItUnique(entity);
        Optional<Long> optionalUser = Optional.empty();
        Optional<UserRole> optionalUserRole = Optional.empty();

        if (createdUserId) {
            List<UserRole> userRoles = userRepository.findUserRoleByUserRoleName("USER");
            for (UserRole userRole : userRoles) {
                    optionalUserRole = Optional.ofNullable(userRole);

            }
                User user = new User(entity.getUsername(), entity.getPassword(),optionalUserRole.get());
                optionalUser = userRepository.save(user);
        }
        return optionalUser.isPresent() && optionalUser.get() > 0;
    }

    private Boolean isItUnique(UserSignupRequestDto entity) {
        if (!this.findAllUsers().isEmpty()) {
            for (UserResponseDto item: this.findAllUsers()){
                if (item.getUsername().equals(entity.getUsername())){
                    return false;
                }
            }
        }
        return true;
    }



    @Override
    public User findById(Long id) {
        Optional<User> findById = userRepository.findById(id);
        return findById.orElse(null);
        }

    @Override
    public List<User> findAll() {
        return Collections.emptyList();
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
    public Optional<UserResponseDto> findByUserName(UserSignupRequestDto userSignupRequestDto) {
        for (UserResponseDto item: this.findAllUsers()){
            if (item.getUsername().equals(userSignupRequestDto.getUsername())){
                if (item.getPassword().equals(userSignupRequestDto.getPassword())){
                    return Optional.of(item);
                }
            }
        }
        return Optional.empty();
    }


}
