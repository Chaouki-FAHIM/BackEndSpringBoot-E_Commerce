package ecommerce.ma.appecommerce.repository;

import ecommerce.ma.appecommerce.model.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur,Long> {
    Utilisateur findByEmail(String email);
    Utilisateur findByPasswd(String passwd);
    Utilisateur findByEmailAndPasswd(String email,String passwd);

}
