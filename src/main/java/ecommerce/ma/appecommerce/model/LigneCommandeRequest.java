package ecommerce.ma.appecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LigneCommandeRequest {
    private Long id; // Id du produit
    private int quantite;
}
