package ir.maktabsharif.usersignuploginapplication.service;

import ir.maktabsharif.usersignuploginapplication.model.dto.UserSignupRequestDto;
import ir.maktabsharif.usersignuploginapplication.model.entity.User;
import ir.maktabsharif.usersignuploginapplication.model.dto.UserResponseDto;
import ir.maktabsharif.usersignuploginapplication.service.base.BaseService;

import java.util.List;
import java.util.Optional;

public interface UserService extends BaseService<UserSignupRequestDto,User,Long> {

    List<UserResponseDto> findAllUsers();

    Optional<UserResponseDto> findByUserNameAndPassword(UserSignupRequestDto userSignupRequestDto);
}
