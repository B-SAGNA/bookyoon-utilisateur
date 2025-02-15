package sn.sonatel.dsi.ins.imoc.service.dto;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;
import sn.sonatel.dsi.ins.imoc.domain.Vehicule;

/**
 * A DTO for the {@link sn.sonatel.dsi.ins.imoc.domain.Vehicule} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class VehiculeDTO implements Serializable {

    private Long id;

    @NotNull
    private String marque;

    @NotNull
    private String modele;

    private String couleur;

    @NotNull
    private String matricule;

    @NotNull
    private Integer place;

    public VehiculeDTO() {
        // Constructor without parameters for Jackson
    }

    public VehiculeDTO(Vehicule vehicule) {
        this.marque = vehicule.getMarque();
        this.modele = vehicule.getModele();
        this.couleur = vehicule.getCouleur();
        this.matricule = vehicule.getMatricule();
        this.place = vehicule.getPlace();
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VehiculeDTO)) {
            return false;
        }

        VehiculeDTO vehiculeDTO = (VehiculeDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, vehiculeDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VehiculeDTO{" +
            "id=" + getId() +
            ", marque='" + getMarque() + "'" +
            ", modele='" + getModele() + "'" +
            ", couleur='" + getCouleur() + "'" +
            ", matricule='" + getMatricule() + "'" +
            ", place=" + getPlace() +
            "}";
    }
}
