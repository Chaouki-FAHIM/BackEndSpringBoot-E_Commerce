package ecommerce.ma.appecommerce.exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
        super("Utilisateur est non trouvé");
    }
}
