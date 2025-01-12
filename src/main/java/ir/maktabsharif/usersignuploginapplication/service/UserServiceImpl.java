package ir.maktabsharif.usersignuploginapplication.service;

import ir.maktabsharif.usersignuploginapplication.model.entity.UserRole;
import ir.maktabsharif.usersignuploginapplication.model.dto.UserSignupRequestDto;
import ir.maktabsharif.usersignuploginapplication.model.entity.User;
import ir.maktabsharif.usersignuploginapplication.model.dto.UserResponseDto;
import ir.maktabsharif.usersignuploginapplication.repository.*;
import ir.maktabsharif.usersignuploginapplication.security.BCryptPasswordEncode;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private UserRepository userRepository = new UserRepositoryImpl();

    @Override
    public Boolean save(UserSignupRequestDto requestDto) {

        String hashedPass =
                BCryptPasswordEncode.encodeBCryptPassword(requestDto.getPassword());

        if (this.isItUnique(requestDto)) {
            Optional<Long> idOfSavedEntity = userRepository.save(
                    User.builder()
                            .username(requestDto.getUsername())
                            .password(hashedPass)
                            .userRole(userRepository.findUserRoleByUserRoleName("USER"))
                            .build());
            return idOfSavedEntity.isPresent();
        }
        return false;
    }

    private Boolean isItUnique(UserSignupRequestDto requestDto) {
        return !userRepository.userNameExists(requestDto.getUsername());
    }


    @Override
    public List<UserResponseDto> findAllUsers() {
        List<User> allUsers = userRepository.findAll();
        return allUsers.stream()
                .map(user -> UserResponseDto.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .userRole(user.getUserRole())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserResponseDto> findByUserNameAndPassword(UserSignupRequestDto userSignupRequestDto) {
        for (UserResponseDto item : this.findAllUsers()) {
            if (item.getUsername().equals(userSignupRequestDto.getUsername())) {
                if (BCryptPasswordEncode.verifyBCryptPassword(userSignupRequestDto.getPassword(), item.getPassword())) {
                    return Optional.of(item);
                }
            }
        }
        return Optional.empty();
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

}
