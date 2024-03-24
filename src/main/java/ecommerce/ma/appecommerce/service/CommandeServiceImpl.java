package ecommerce.ma.appecommerce.service;

import ecommerce.ma.appecommerce.model.CommandeRequest;
import ecommerce.ma.appecommerce.model.LigneCommandeRequest;
import ecommerce.ma.appecommerce.model.entity.Commande;
import ecommerce.ma.appecommerce.model.entity.LigneCommande;
import ecommerce.ma.appecommerce.model.entity.Produit;
import ecommerce.ma.appecommerce.model.entity.Utilisateur;
import ecommerce.ma.appecommerce.repository.CommandeRepository;
import ecommerce.ma.appecommerce.repository.LigneCommandeRepository;
import ecommerce.ma.appecommerce.repository.ProduitRepository;
import ecommerce.ma.appecommerce.repository.UtilisateurRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommandeServiceImpl implements IService<CommandeRequest,Commande,Long> {

    @Autowired
    private CommandeRepository commandeRepository;
    @Autowired
    private LigneCommandeRepository ligneCommandeRepository;
    @Autowired
    private UtilisateurRepository userRepository;
    @Autowired
    private ProduitRepository produitRepository;


    @Override
    public List<Commande> fetchAll() {
        return null;
    }

    @Override
    public Commande searcheByID(Long aLong) {
        return null;
    }

    @Override
    @Transactional
    public Commande add(CommandeRequest commandeRequest) {
        long utilisateurId = commandeRequest.getUtilisateurId();
        List<LigneCommandeRequest> ligneCommandeRequests = commandeRequest.getProduits();

        Utilisateur utilisateur = userRepository.findById(utilisateurId)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur non trouvé avec l'id " + utilisateurId));

        Commande commande = new Commande();
        commande.setUtilisateur(utilisateur);
        commande.setDateCommande(new Date());
        commande = commandeRepository.save(commande);

        for (LigneCommandeRequest ligneCommandeRequest : ligneCommandeRequests) {
            Produit produit = produitRepository.findById(ligneCommandeRequest.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Produit non trouvé avec l'id " + ligneCommandeRequest.getId()));

            LigneCommande ligneCommande = new LigneCommande();
            ligneCommande.setCommande(commande);
            ligneCommande.setProduit(produit);
            ligneCommande.setQuantite(ligneCommandeRequest.getQuantite());
            ligneCommandeRepository.save(ligneCommande);
        }

        return commande;
    }

    @Override
    public Commande update(CommandeRequest commandeRequest, Long aLong) {
        return null;
    }

    @Override
    public void delete(Long aLong) {
        return;
    }
}

