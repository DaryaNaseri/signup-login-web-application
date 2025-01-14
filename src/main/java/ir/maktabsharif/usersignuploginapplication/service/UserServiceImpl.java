package ir.maktabsharif.usersignuploginapplication.service;

import ir.maktabsharif.usersignuploginapplication.model.dto.*;
import ir.maktabsharif.usersignuploginapplication.model.entity.User;
import ir.maktabsharif.usersignuploginapplication.repository.*;
import ir.maktabsharif.usersignuploginapplication.security.BCryptPasswordEncode;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private UserRepository userRepository = new UserRepositoryImpl();

    @Override
    public Boolean save(SignupRequestDto requestDto) {

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

    private Boolean isItUnique(SignupRequestDto requestDto) {
        return !userRepository.userNameExists(requestDto.getUsername());
    }


    @Override
    public List<ResponseDto> findAllUsers() {
        List<User> allUsers = userRepository.findAll();
        return allUsers.stream()
                .map(user -> ResponseDto.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .email(user.getEmail())
                        .phone(user.getPhone())
                        .fullName(user.getFullName())
                        .age(user.getAge())
                        .password(user.getPassword())
                        .photoHash(user.getPhotoHash())
                        .userRole(user.getUserRole())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ResponseDto> findByUserNameAndPassword(LoginRequestDto loginRequestDto) {
        for (ResponseDto item : this.findAllUsers()) {
            if (item.getUsername().equals(loginRequestDto.getUsername())) {
                if (BCryptPasswordEncode.verifyBCryptPassword(loginRequestDto.getPassword(), item.getPassword())) {
                    return Optional.of(item);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public Boolean update(EditRequestDto newRequestDto) {
        User user = User
                .builder()
                .id(newRequestDto.getId())
                .photoHash(newRequestDto.getPhotoHash())
                .email(newRequestDto.getEmail())
                .phone(newRequestDto.getPhone())
                .fullName(newRequestDto.getFullName())
                .age(newRequestDto.getAge())
                .userRole(userRepository.findUserRoleByUserRoleName("USER"))
                .build();


        Optional<User> userOpt = userRepository.update(user);

        return userOpt.isPresent();
    }

    @Override
    public Boolean updateUserOrPass(EditUserPassDto editUserPassDto) {
        Optional<User> update = userRepository.update(User
                .builder()
                .id(editUserPassDto.getId())
                .username(editUserPassDto.getUsername())
                .password(BCryptPasswordEncode.encodeBCryptPassword(editUserPassDto.getPassword())).
                build());
        return update.isPresent();
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
