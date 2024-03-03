package ecommerce.ma.appecommerce.controller;

import ecommerce.ma.appecommerce.model.entity.Categorie;
import ecommerce.ma.appecommerce.model.entity.Produit;
import ecommerce.ma.appecommerce.repository.CategorieRepository;
import ecommerce.ma.appecommerce.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
@CrossOrigin("http://localhost:3000")
@RestController
public class ProduitController {

    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private CategorieRepository categorieRepository;

    @PostMapping("/produits")
    public Produit newProduit(@RequestBody Produit produit) {
        Categorie categorie = categorieRepository.findById(produit.getCategorie().getId())
                .orElseThrow(() -> new RuntimeException("Catégorie non trouvée avec l'ID : " + produit.getCategorie().getId()));
        produit.setCategorie(categorie);
        produit.setDate(new Date());
        return produitRepository.save(produit);
    }

    @GetMapping("/produits")
    List<Produit> showAllProduit(){
        return produitRepository.findAll();
    }

    @GetMapping("/produits/{id}")
    Produit getProduitById(@PathVariable Long id) {
        return produitRepository.findById(id).map(produit -> {
            Categorie categorie = categorieRepository.findById(produit.getCategorie().getId())
                    .orElse(null);
            produit.setCategorie(categorie);
            return produit;
        }).orElse(null);
    }

    @GetMapping("/produits/categorie/{id}")
    List<Produit> getProduitsByCategorieId(@PathVariable Long id) {
        return produitRepository.findByCategorieId(id);
    }

    @PutMapping("/produits/{id}")
    public ResponseEntity<Produit> updateProduit(@PathVariable Long id, @RequestBody Produit updatedProduit) {
        Produit existingProduit = produitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé avec l'ID : " + id));

        existingProduit.setLibelle(updatedProduit.getLibelle());
        existingProduit.setPrix(updatedProduit.getPrix());
        existingProduit.setCategorie(updatedProduit.getCategorie());

        Produit savedProduit = produitRepository.save(existingProduit);
        return ResponseEntity.ok(savedProduit);
    }

    @DeleteMapping("/produits/{id}")
    ResponseEntity<String> deleteProduit(@PathVariable Long id){
        try {
            produitRepository.deleteById(id);
            return ResponseEntity.ok("Suppression avec succès pour le produit !!" );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la suppression du produit.");
        }
    }

}
