package ecommerce.ma.appecommerce.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommandeRequest {
    @JsonProperty("utilisateur_id")
    private Long utilisateurId;
    private List<LigneCommandeRequest> produits;
}
