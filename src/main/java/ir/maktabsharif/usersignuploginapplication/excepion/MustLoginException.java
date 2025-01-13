package ir.maktabsharif.usersignuploginapplication.excepion;

public class MustLoginException extends RuntimeException{
    public MustLoginException(String message) {
        super(message);
    }
}
