package ecommerce.ma.appecommerce.repository;

import ecommerce.ma.appecommerce.model.entity.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long> {

}
