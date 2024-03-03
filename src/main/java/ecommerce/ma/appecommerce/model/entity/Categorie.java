package ecommerce.ma.appecommerce.model.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "Categorie")
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="matricule")
    @NonNull
    private String matricule;
    @Column(name="libelle")
    @NonNull
    private String libelle;
}
