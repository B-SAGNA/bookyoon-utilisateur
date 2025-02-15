package sn.sonatel.dsi.ins.imoc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "vehicule")
public class Vehicule implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotNull
    @Column(name = "marque", nullable = false, length = 50)
    @Size(max = 50)
    private String marque;

    @NotNull
    @Column(name = "modele", nullable = false, length = 50)
    @Size(max = 50)
    private String modele;

    @NotNull
    @Column(name = "couleur", nullable = false, length = 30)
    @Size(max = 30)
    private String couleur;

    @NotNull
    @Column(name = "matricule", nullable = false, unique = true, length = 20)
    @Size(max = 20)
    private String matricule;

    @NotNull
    @Column(name = "place", nullable = false)
    private Integer place;

    @JsonIgnoreProperties(value = { "vehicule" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "vehicule")
    private User user;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public Integer getPlace() {
        return place;
    }

    public void setPlace(Integer place) {
        this.place = place;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        if (this.user != null) {
            this.user.setVehicule(null);
        }
        if (user != null) {
            user.setVehicule(this);
        }
        this.user = user;
    }

    @Override
    public String toString() {
        return (
            "Vehicule{" +
            ", marque='" +
            marque +
            '\'' +
            ", modele='" +
            modele +
            '\'' +
            ", couleur='" +
            couleur +
            '\'' +
            ", matricule='" +
            matricule +
            '\'' +
            ", place=" +
            place +
            '}'
        );
    }
}
