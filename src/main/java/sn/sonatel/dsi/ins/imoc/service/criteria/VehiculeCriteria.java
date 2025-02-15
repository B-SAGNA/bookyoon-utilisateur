package sn.sonatel.dsi.ins.imoc.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.IntegerFilter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link sn.sonatel.dsi.ins.imoc.service.criteria.criteriaVehicule} entity. This class is used
 * in {@link sn.sonatel.dsi.ins.imoc.service.criteria.VehiculeResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /vehicules?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class VehiculeCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter marque;

    private StringFilter modele;

    private StringFilter couleur;

    private StringFilter matricule;

    private IntegerFilter place;

    private LongFilter utilisateurId;

    private Boolean distinct;

    public VehiculeCriteria() {}

    public VehiculeCriteria(VehiculeCriteria other) {
        this.id = other.optionalId().map(LongFilter::copy).orElse(null);
        this.marque = other.optionalMarque().map(StringFilter::copy).orElse(null);
        this.modele = other.optionalModele().map(StringFilter::copy).orElse(null);
        this.couleur = other.optionalCouleur().map(StringFilter::copy).orElse(null);
        this.matricule = other.optionalMatricule().map(StringFilter::copy).orElse(null);
        this.place = other.optionalPlace().map(IntegerFilter::copy).orElse(null);
        this.utilisateurId = other.optionalUtilisateurId().map(LongFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public VehiculeCriteria copy() {
        return new VehiculeCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public Optional<LongFilter> optionalId() {
        return Optional.ofNullable(id);
    }

    public LongFilter id() {
        if (id == null) {
            setId(new LongFilter());
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getMarque() {
        return marque;
    }

    public Optional<StringFilter> optionalMarque() {
        return Optional.ofNullable(marque);
    }

    public StringFilter marque() {
        if (marque == null) {
            setMarque(new StringFilter());
        }
        return marque;
    }

    public void setMarque(StringFilter marque) {
        this.marque = marque;
    }

    public StringFilter getModele() {
        return modele;
    }

    public Optional<StringFilter> optionalModele() {
        return Optional.ofNullable(modele);
    }

    public StringFilter modele() {
        if (modele == null) {
            setModele(new StringFilter());
        }
        return modele;
    }

    public void setModele(StringFilter modele) {
        this.modele = modele;
    }

    public StringFilter getCouleur() {
        return couleur;
    }

    public Optional<StringFilter> optionalCouleur() {
        return Optional.ofNullable(couleur);
    }

    public StringFilter couleur() {
        if (couleur == null) {
            setCouleur(new StringFilter());
        }
        return couleur;
    }

    public void setCouleur(StringFilter couleur) {
        this.couleur = couleur;
    }

    public StringFilter getMatricule() {
        return matricule;
    }

    public Optional<StringFilter> optionalMatricule() {
        return Optional.ofNullable(matricule);
    }

    public StringFilter matricule() {
        if (matricule == null) {
            setMatricule(new StringFilter());
        }
        return matricule;
    }

    public void setMatricule(StringFilter matricule) {
        this.matricule = matricule;
    }

    public IntegerFilter getPlace() {
        return place;
    }

    public Optional<IntegerFilter> optionalPlace() {
        return Optional.ofNullable(place);
    }

    public IntegerFilter place() {
        if (place == null) {
            setPlace(new IntegerFilter());
        }
        return place;
    }

    public void setPlace(IntegerFilter place) {
        this.place = place;
    }

    public LongFilter getUtilisateurId() {
        return utilisateurId;
    }

    public Optional<LongFilter> optionalUtilisateurId() {
        return Optional.ofNullable(utilisateurId);
    }

    public LongFilter utilisateurId() {
        if (utilisateurId == null) {
            setUtilisateurId(new LongFilter());
        }
        return utilisateurId;
    }

    public void setUtilisateurId(LongFilter utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public Optional<Boolean> optionalDistinct() {
        return Optional.ofNullable(distinct);
    }

    public Boolean distinct() {
        if (distinct == null) {
            setDistinct(true);
        }
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final VehiculeCriteria that = (VehiculeCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(marque, that.marque) &&
            Objects.equals(modele, that.modele) &&
            Objects.equals(couleur, that.couleur) &&
            Objects.equals(matricule, that.matricule) &&
            Objects.equals(place, that.place) &&
            Objects.equals(utilisateurId, that.utilisateurId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, marque, modele, couleur, matricule, place, utilisateurId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VehiculeCriteria{" +
            optionalId().map(f -> "id=" + f + ", ").orElse("") +
            optionalMarque().map(f -> "marque=" + f + ", ").orElse("") +
            optionalModele().map(f -> "modele=" + f + ", ").orElse("") +
            optionalCouleur().map(f -> "couleur=" + f + ", ").orElse("") +
            optionalMatricule().map(f -> "matricule=" + f + ", ").orElse("") +
            optionalPlace().map(f -> "place=" + f + ", ").orElse("") +
            optionalUtilisateurId().map(f -> "utilisateurId=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
