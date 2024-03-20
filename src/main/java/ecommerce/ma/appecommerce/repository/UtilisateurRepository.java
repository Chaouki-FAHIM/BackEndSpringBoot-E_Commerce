package ecommerce.ma.appecommerce.repository;

import ecommerce.ma.appecommerce.model.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur,Long> {
    Utilisateur findByUsername(String username);
    Utilisateur findByPassword(String passwd);
    Utilisateur findByUsernameAndPassword(String username, String password);
}
