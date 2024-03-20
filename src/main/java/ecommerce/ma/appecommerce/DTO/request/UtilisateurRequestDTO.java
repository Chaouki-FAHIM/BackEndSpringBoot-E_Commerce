package ecommerce.ma.appecommerce.DTO.request;

import ecommerce.ma.appecommerce.model.Role;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Role role;
}
