package hu.vereba.snippet.cm.rest.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Validated
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Abstract",
        "AgeRating",
        "AvailabilityFromUtcIso",
        "BackgroundUrl",
        "Cast",
        "Category",
        "Director",
        "EditedAbstract",
        "Genre",
        "Id",
        "Name",
        "ProductionYear"})
public class Show implements Serializable {

    @JsonProperty("Abstract")
    @NotNull
    private String abstractDesc;
    @JsonProperty("AgeRating")
    @Min(value = 0, message = "Age rating should not be less than 0")
    @Max(value = 21, message = "Age rating should not be greater than 21")
    @NotNull
    private int ageRating;
    @JsonProperty("AvailabilityFromUtcIso")
    @NotNull
    private LocalDateTime availabilityFromUtcIso;
    @JsonProperty("BackgroundUrl")
    @NotNull
    private String backgroundUrl;
    @JsonProperty("Cast")
    @NotNull
    private String cast;
    @JsonProperty("Category")
    private ShowType category;
    @JsonProperty("Director")
    @NotNull
    private String director;
    @JsonProperty("EditedAbstract")
    @NotNull
    private String editedAbstract;
    @JsonProperty("Genre")
    @NotNull
    private String genre;
    @JsonProperty("Id")
    @NotNull
    private String id;
    @JsonProperty("Name")
    @NotNull
    private String name;
    @JsonProperty("ProductionYear")
    @Min(value = 1874, message = "Production year shall be after 1874")
    @NotNull
    private int productionYear;
    @JsonProperty("Seasons")
    @Min(value = 1, message = "Series has at least one season")
    @Max(value = 100, message = "Series has maximum of 100 seasons")
    @NotNull
    private Integer seasons;

    public Show abstractDesc(String abstractDesc) {
        this.abstractDesc = abstractDesc;
        return this;
    }

    public String getAbstractDesc() {
        return abstractDesc;
    }

    public void setAbstractDesc(String abstractDesc) {
        this.abstractDesc = abstractDesc;
    }

    public Show ageRating(int ageRating) {
        this.ageRating = ageRating;
        return this;
    }

    public int getAgeRating() {
        return ageRating;
    }

    public void setAgeRating(int ageRating) {
        this.ageRating = ageRating;
    }

    public Show availabilityFromUtcIso(LocalDateTime availabilityFromUtcIso) {
        this.availabilityFromUtcIso = availabilityFromUtcIso;
        return this;
    }

    public LocalDateTime getAvailabilityFromUtcIso() {
        return availabilityFromUtcIso;
    }

    public void setAvailabilityFromUtcIso(LocalDateTime availabilityFromUtcIso) {
        this.availabilityFromUtcIso = availabilityFromUtcIso;
    }

    public Show backgroundUrl(String backgroundUrl) {
        this.backgroundUrl = backgroundUrl;
        return this;
    }

    public String getBackgroundUrl() {
        return backgroundUrl;
    }

    public void setBackgroundUrl(String backgroundUrl) {
        this.backgroundUrl = backgroundUrl;
    }

    public Show cast(String cast) {
        this.cast = cast;
        return this;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public Show category(ShowType category) {
        this.category = category;
        return this;
    }

    public ShowType getCategory() {
        return category;
    }

    public void setCategory(ShowType category) {
        this.category = category;
    }

    public Show director(String director) {
        this.director = director;
        return this;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Show editedAbstract(String editedAbstract) {
        this.editedAbstract = editedAbstract;
        return this;
    }

    public String getEditedAbstract() {
        return editedAbstract;
    }

    public void setEditedAbstract(String editedAbstract) {
        this.editedAbstract = editedAbstract;
    }

    public Show genre(String genre) {
        this.genre = genre;
        return this;
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

    public Show name(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Show productionYear(int productionYear) {
        this.productionYear = productionYear;
        return this;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Show that = (Show) o;
        return productionYear == that.productionYear &&
                id.equals(that.id) &&
                name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, productionYear);
    }

    @Override
    public String toString() {
        return "Show{" +
                "abstractDesc='" + abstractDesc + '\'' +
                ", ageRating=" + ageRating +
                ", availabilityFromUtcIso=" + availabilityFromUtcIso +
                ", backgroundUrl='" + backgroundUrl + '\'' +
                ", cast='" + cast + '\'' +
                ", category='" + category + '\'' +
                ", director='" + director + '\'' +
                ", editedAbstract='" + editedAbstract + '\'' +
                ", genre='" + genre + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", productionYear=" + productionYear +
                ", seasons=" + seasons +
                '}';
    }
}
