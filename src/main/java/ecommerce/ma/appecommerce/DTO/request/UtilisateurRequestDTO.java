package ecommerce.ma.appecommerce.DTO.request;

import ecommerce.ma.appecommerce.model.Role;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UtilisateurRequestDTO {
    private String nom;
    private String prenom;
    private String username;
    private String adresse;
    private String email;
    private String password;
    private Set<Role> roles;
}
