package ecommerce.ma.appecommerce.exception;

public class UserCreationException extends Exception {
    public UserCreationException() {
        super("Email et/ou mot de passe sont invalides");
    }
}
