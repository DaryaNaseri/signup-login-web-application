package ir.maktabsharif.usersignuploginapplication.service;

import ir.maktabsharif.usersignuploginapplication.model.dto.UserRequestDto;
import ir.maktabsharif.usersignuploginapplication.model.User;
import ir.maktabsharif.usersignuploginapplication.model.dto.UserResponseDto;
import ir.maktabsharif.usersignuploginapplication.service.base.BaseService;

import java.util.List;

public interface UserService extends BaseService<UserRequestDto,User,Long> {

    List<UserResponseDto> findAllUsers();

    Boolean findByUserName(UserRequestDto userRequestDto);
}
