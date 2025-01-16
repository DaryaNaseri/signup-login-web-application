package ir.maktabsharif.usersignuploginapplication.service;

import ir.maktabsharif.usersignuploginapplication.model.dto.*;
import ir.maktabsharif.usersignuploginapplication.model.entity.User;
import ir.maktabsharif.usersignuploginapplication.service.base.BaseService;

import java.util.List;
import java.util.Optional;

public interface UserService extends BaseService<SignupRequestDto,User,Long> {

    List<ResponseDto> findAllUsers();

    Optional<ResponseDto> findByUserNameAndPassword(LoginRequestDto loginRequestDto);

    Optional<ResponseDto> update(EditRequestDto newRequestDto);

    Optional<ResponseDto> updateUserOrPass(EditRequestDto editUserPassDto);


}
