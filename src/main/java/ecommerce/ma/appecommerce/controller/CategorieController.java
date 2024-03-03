package ecommerce.ma.appecommerce.controller;

import ecommerce.ma.appecommerce.model.entity.Categorie;
import ecommerce.ma.appecommerce.model.entity.Produit;
import ecommerce.ma.appecommerce.repository.CategorieRepository;
import ecommerce.ma.appecommerce.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin("http://localhost:3000")
@RestController
public class CategorieController {

    @Autowired
    private CategorieRepository categorieRepository;

    @Autowired
    private ProduitRepository produitRepository;

    @PostMapping("/categories")
    Categorie newCategorie(@RequestBody Categorie categorie){
        categorie.setMatricule(UUID.randomUUID().toString());
        return categorieRepository.save(categorie);
    }

    @GetMapping("/categories")
    List<Categorie> showAllCategories(){
        return categorieRepository.findAll();
    }

    @GetMapping("/categories/{id}")
    ResponseEntity<Categorie> getCategorieById(@PathVariable Long id) {
        Categorie categorie = categorieRepository.findById(id).orElse(null);
        if (categorie != null) {
            return ResponseEntity.ok(categorie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/categories/{id}")
    Categorie updateCategorie(@RequestBody Categorie newCategorie, @PathVariable Long id) {
        return categorieRepository.findById(id)
                .map(categorie -> {
                    categorie.setLibelle(newCategorie.getLibelle());
                    return categorieRepository.save(categorie);
                })
                .orElseGet(() -> {
                    newCategorie.setId(id);
                    return categorieRepository.save(newCategorie);
                });
    }

    @DeleteMapping("/categories/{id}")
    ResponseEntity deleteCategorie(@PathVariable Long id){
        List<Produit> produits = produitRepository.findByCategorieId(id);
        if (!produits.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Impossible de supprimer la catégorie car elle est associée à un produit au minimum.");
        }

        categorieRepository.deleteById(id);
        return ResponseEntity.ok("Suppression avec succès pour cette catégorie : " + id);
    }

}
