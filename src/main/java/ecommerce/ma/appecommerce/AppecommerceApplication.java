package ecommerce.ma.appecommerce;

import ecommerce.ma.appecommerce.model.Role;
import ecommerce.ma.appecommerce.model.entity.Categorie;
import ecommerce.ma.appecommerce.model.entity.Produit;
import ecommerce.ma.appecommerce.model.entity.Utilisateur;
import ecommerce.ma.appecommerce.repository.CategorieRepository;
import ecommerce.ma.appecommerce.repository.ProduitRepository;
import ecommerce.ma.appecommerce.repository.UtilisateurRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;


@SpringBootApplication
public class AppecommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppecommerceApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(ProduitRepository produitRepository, UtilisateurRepository utilisateurRepository, CategorieRepository categorieRepository) {
		return args -> {
			// Initialize products
			Categorie electonique = Categorie.builder()
					.libelle("Eléctonique")
					.matricule(UUID.randomUUID().toString())
					.build();
			Categorie vetement = Categorie.builder()
					.libelle("Vêtement")
					.matricule(UUID.randomUUID().toString())
					.build();
			Categorie console = Categorie.builder()
					.libelle("Console")
					.matricule(UUID.randomUUID().toString())
					.build();
			categorieRepository.save(electonique);
			categorieRepository.save(vetement);
			categorieRepository.save(console);

			// Initialize products
			produitRepository.save(
					Produit.builder()
					.libelle("Smart watch")
					.categorie(electonique)
					.image("https://ma.jumia.is/unsafe/fit-in/500x500/filters:fill(white)/product/02/178885/1.jpg?1269")
					.prix(100.0)
					.date(new Date())
					.build());
			produitRepository.save(
					Produit.builder()
					.libelle("Chemise professionnelle d'été")
					.categorie(vetement)
					.image("https://img.freepik.com/photos-gratuite/tas-jeans-tordus-gros-plan-vetements-mode_169016-4773.jpg?w=1380&t=st=1708554366~exp=1708554966~hmac=c1691f88b25973b895afd71c06de82fb97d7b1727dd5ec52f3aa251787510db3")
					.prix(200.0)
					.date(new Date())
					.build());

			// Initialize users
			utilisateurRepository.save(
					Utilisateur.builder()
					.nom("Emma")
					.prenom("Johnson")
					.email("admin@gmail.com")
					.role(Role.ADMIN)
					.adress("456 Main Avenue")
					.passwd("admin")
					.dateCreation(new Date())
					.build()
			);

			utilisateurRepository.save(
					Utilisateur.builder()
					.nom("Liam")
					.prenom("Smith")
					.email("customer@gmail.com")
					.role(Role.CUSTOMER)
					.adress("171 Bd Mohammed Zerktouni, Casablanca")
					.passwd("customer")
					.dateCreation(new Date())
					.build()
			);
		};
	}
}
