package ecommerce.ma.appecommerce.DTO.request;

import ecommerce.ma.appecommerce.model.entity.Categorie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProduitRequestDTO {
    private String libelle;
    private Double prix;
    private String image;
    private Categorie categorie;
}
