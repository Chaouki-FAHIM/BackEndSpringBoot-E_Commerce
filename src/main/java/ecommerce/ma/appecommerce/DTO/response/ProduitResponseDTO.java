package ecommerce.ma.appecommerce.DTO.response;

import ecommerce.ma.appecommerce.model.entity.Categorie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProduitResponseDTO {
    private Long id;
    private String libelle;
    private Double prix;
    private String image;
    private Categorie categorie;
    private Date date;
}
