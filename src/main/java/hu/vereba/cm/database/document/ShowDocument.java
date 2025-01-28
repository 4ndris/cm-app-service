package hu.vereba.cm.database.document;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.OffsetDateTime;

@Document(collection = "shows")
//@Getter
//@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true) // Include only key fields
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = false) // Ensures standard JavaBean accessors
public class ShowDocument {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    private String abstractDesc;
    private int ageRating;
    private OffsetDateTime availabilityFromUtcIso;
    private String backgroundUrl;
    private String cast;
    private String category; // MOVIE or SERIES
    private String director;
    private String editedAbstract;
    private String genre;
    private String imdbId;
    private String name;
    private int productionYear;

    // Optional field for series
    private Integer seasons;

    //Getters-Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public OffsetDateTime getAvailabilityFromUtcIso() {
        return availabilityFromUtcIso;
    }

    public void setAvailabilityFromUtcIso(OffsetDateTime availabilityFromUtcIso) {
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

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
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

    public Integer getSeasons() {
        return seasons;
    }

    public void setSeasons(Integer seasons) {
        this.seasons = seasons;
    }
}
