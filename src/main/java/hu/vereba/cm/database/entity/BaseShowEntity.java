package hu.vereba.cm.database.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "category", discriminatorType = DiscriminatorType.STRING )
@Table(name = "SHOW")
public class BaseShowEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long itemId;

    @Column(name = "abstract")
    private String abstractDesc;
    private int ageRating;
    private LocalDateTime availabilityFromUtcIso;
    private String backgroundUrl;
    private String cast;
    @Column(name = "category", insertable=false, updatable=false) //discriminator column
    private String category;
    private String director;
    private String editedAbstract;
    private String genre;
    private String id;
    private String name;
    private int productionYear;

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }


    public String getAbstractDesc() {
        return abstractDesc;
    }

    public void setAbstractDesc(String abstractDesc) {
        this.abstractDesc = abstractDesc;
    }


    public int getAgeRating() {
        return ageRating;
    }

    public void setAgeRating(int ageRating) {
        this.ageRating = ageRating;
    }


    public LocalDateTime getAvailabilityFromUtcIso() {
        return availabilityFromUtcIso;
    }

    public void setAvailabilityFromUtcIso(LocalDateTime availabilityFromUtcIso) {
        this.availabilityFromUtcIso = availabilityFromUtcIso;
    }


    public String getBackgroundUrl() {
        return backgroundUrl;
    }

    public void setBackgroundUrl(String backgroundUrl) {
        this.backgroundUrl = backgroundUrl;
    }


    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }


    public String getEditedAbstract() {
        return editedAbstract;
    }

    public void setEditedAbstract(String editedAbstract) {
        this.editedAbstract = editedAbstract;
    }


    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseShowEntity that = (BaseShowEntity) o;
        return productionYear == that.productionYear &&
                id.equals(that.id) &&
                name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, productionYear);
    }
}
