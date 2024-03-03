package ecommerce.ma.appecommerce.DTO.response;

import ecommerce.ma.appecommerce.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UtilisateurResponseDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String adress;
    private String email;
    private String passwd;
    private Role role;
    private Date dateCreation;
}
