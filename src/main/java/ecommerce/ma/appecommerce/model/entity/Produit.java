    package ecommerce.ma.appecommerce.model.entity;

    import jakarta.persistence.*;
    import lombok.*;

    import java.util.Date;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    @Entity
    @Table(name = "Produit")
    public class Produit {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="id")
        private Long id;

        @Column(name="libelle")
        @NonNull
        private String libelle;

        @Column(name="prix")
        @NonNull
        private Double prix;

        @Column(name="image")
        private String image;

        @Column(name="date")
        @Temporal(TemporalType.TIMESTAMP)
        private Date date;

        @ManyToOne
        @JoinColumn(name = "categorie_id", referencedColumnName = "id")
        @NonNull
        private Categorie categorie;

        @OneToOne(mappedBy = "produit")
        private StockProduit stockProduit;
    }
