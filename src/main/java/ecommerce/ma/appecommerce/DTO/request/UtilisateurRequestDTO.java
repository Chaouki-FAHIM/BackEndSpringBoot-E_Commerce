package ecommerce.ma.appecommerce.DTO.request;

import ecommerce.ma.appecommerce.model.Role;
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
    private String adresse;
    private String email;
    private String passwd;
    private Role role;
}
