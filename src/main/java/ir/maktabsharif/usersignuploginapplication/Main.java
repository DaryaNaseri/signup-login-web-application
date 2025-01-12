package ir.maktabsharif.usersignuploginapplication;


import ir.maktabsharif.usersignuploginapplication.model.dto.UserSignupRequestDto;
import ir.maktabsharif.usersignuploginapplication.service.UserService;
import ir.maktabsharif.usersignuploginapplication.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.save(new UserSignupRequestDto("jjfjjfjjf","jjfjjfjjfj"));
    }
}
