package ir.maktabsharif.usersignuploginapplication.excepion;

public class UsernameOrPasswordIncorrectException extends RuntimeException{
    public UsernameOrPasswordIncorrectException(String message) {
        super(message);
    }
}
