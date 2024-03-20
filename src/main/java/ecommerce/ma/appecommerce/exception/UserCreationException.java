package ecommerce.ma.appecommerce.exception;

public class UserCreationException extends Exception {
    public UserCreationException() {
        super("Username et/ou mot de passe sont invalides");
    }
}
