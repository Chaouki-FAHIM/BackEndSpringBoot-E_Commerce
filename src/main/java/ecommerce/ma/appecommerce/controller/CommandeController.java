package ecommerce.ma.appecommerce.controller;

import ecommerce.ma.appecommerce.exception.NotValidDataException;
import ecommerce.ma.appecommerce.exception.RequiredDataException;
import ecommerce.ma.appecommerce.model.CommandeRequest;
import ecommerce.ma.appecommerce.model.entity.Commande;
import ecommerce.ma.appecommerce.service.CommandeServiceImpl;
import ecommerce.ma.appecommerce.service.IService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("http://localhost:3000")
@RestController
public class CommandeController {

    @Autowired
    private IService<CommandeRequest,Commande,Long> orderService;

    @PostMapping("/commandes")
    public ResponseEntity<?> ajouterCommande(@RequestBody CommandeRequest commandeRequest) {
        try {
            Commande commande = orderService.add(commandeRequest);
            return ResponseEntity.ok(commande);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (RequiredDataException e) {
            throw new RuntimeException(e);
        } catch (NotValidDataException e) {
            throw new RuntimeException(e);
        }
    }
}
