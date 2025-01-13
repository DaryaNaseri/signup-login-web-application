package ir.maktabsharif.usersignuploginapplication.service;

import ir.maktabsharif.usersignuploginapplication.model.dto.EditRequestDto;
import ir.maktabsharif.usersignuploginapplication.model.dto.LoginRequestDto;
import ir.maktabsharif.usersignuploginapplication.model.dto.ResponseDto;
import ir.maktabsharif.usersignuploginapplication.model.dto.SignupRequestDto;
import ir.maktabsharif.usersignuploginapplication.model.entity.User;
import ir.maktabsharif.usersignuploginapplication.service.base.BaseService;

import java.util.List;
import java.util.Optional;

public interface UserService extends BaseService<SignupRequestDto,User,Long> {

    List<ResponseDto> findAllUsers();

    Optional<ResponseDto> findByUserNameAndPassword(LoginRequestDto loginRequestDto);

    Boolean update(EditRequestDto newRequestDto);
}
