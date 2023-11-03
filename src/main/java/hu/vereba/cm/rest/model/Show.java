package hu.vereba.cm.rest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * Show
 */
@JsonInclude(JsonInclude.Include.NON_NULL)

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class Show {

  private String abstractDesc;

  private Integer ageRating;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime availabilityFromUtcIso;

  private String backgroundUrl;

  private String cast;

  /**
   * Gets or Sets category
   */
  public enum CategoryEnum {
    MOVIE("MOVIE"),
    
    SERIES("SERIES");

    private String value;

    CategoryEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static CategoryEnum fromValue(String value) {
      for (CategoryEnum b : CategoryEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private CategoryEnum category;

  private String director;

  private String editedAbstract;

  private String genre;

  private String id;

  private String name;

  private Integer productionYear;

  private Integer seasons;

  public Show() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Show(Integer ageRating, OffsetDateTime availabilityFromUtcIso, String backgroundUrl, String cast, String director, String editedAbstract, String genre, String id, String name, Integer productionYear, Integer seasons) {
    this.ageRating = ageRating;
    this.availabilityFromUtcIso = availabilityFromUtcIso;
    this.backgroundUrl = backgroundUrl;
    this.cast = cast;
    this.director = director;
    this.editedAbstract = editedAbstract;
    this.genre = genre;
    this.id = id;
    this.name = name;
    this.productionYear = productionYear;
    this.seasons = seasons;
  }

  public Show abstractDesc(String abstractDesc) {
    this.abstractDesc = abstractDesc;
    return this;
  }

  /**
   * Get abstractDesc
   * @return abstractDesc
  */
  
  @Schema(name = "AbstractDesc", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("AbstractDesc")
  public String getAbstractDesc() {
    return abstractDesc;
  }

  public void setAbstractDesc(String abstractDesc) {
    this.abstractDesc = abstractDesc;
  }

  public Show ageRating(Integer ageRating) {
    this.ageRating = ageRating;
    return this;
  }

  /**
   * Get ageRating
   * minimum: 0
   * maximum: 21
   * @return ageRating
  */
  @NotNull @Min(0) @Max(21) 
  @Schema(name = "AgeRating", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("AgeRating")
  public Integer getAgeRating() {
    return ageRating;
  }

  public void setAgeRating(Integer ageRating) {
    this.ageRating = ageRating;
  }

  public Show availabilityFromUtcIso(OffsetDateTime availabilityFromUtcIso) {
    this.availabilityFromUtcIso = availabilityFromUtcIso;
    return this;
  }

  /**
   * Get availabilityFromUtcIso
   * @return availabilityFromUtcIso
  */
  @NotNull @Valid 
  @Schema(name = "AvailabilityFromUtcIso", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("AvailabilityFromUtcIso")
  public OffsetDateTime getAvailabilityFromUtcIso() {
    return availabilityFromUtcIso;
  }

  public void setAvailabilityFromUtcIso(OffsetDateTime availabilityFromUtcIso) {
    this.availabilityFromUtcIso = availabilityFromUtcIso;
  }

  public Show backgroundUrl(String backgroundUrl) {
    this.backgroundUrl = backgroundUrl;
    return this;
  }

  /**
   * Get backgroundUrl
   * @return backgroundUrl
  */
  @NotNull 
  @Schema(name = "BackgroundUrl", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("BackgroundUrl")
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

  /**
   * Get cast
   * @return cast
  */
  @NotNull 
  @Schema(name = "Cast", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("Cast")
  public String getCast() {
    return cast;
  }

  public void setCast(String cast) {
    this.cast = cast;
  }

  public Show category(CategoryEnum category) {
    this.category = category;
    return this;
  }

  /**
   * Get category
   * @return category
  */
  
  @Schema(name = "Category", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("Category")
  public CategoryEnum getCategory() {
    return category;
  }

  public void setCategory(CategoryEnum category) {
    this.category = category;
  }

  public Show director(String director) {
    this.director = director;
    return this;
  }

  /**
   * Get director
   * @return director
  */
  @NotNull 
  @Schema(name = "Director", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("Director")
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

  /**
   * Get editedAbstract
   * @return editedAbstract
  */
  @NotNull 
  @Schema(name = "EditedAbstract", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("EditedAbstract")
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

  /**
   * Get genre
   * @return genre
  */
  @NotNull 
  @Schema(name = "Genre", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("Genre")
  public String getGenre() {
    return genre;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }

  public Show id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @NotNull 
  @Schema(name = "Id", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("Id")
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

  /**
   * Get name
   * @return name
  */
  @NotNull 
  @Schema(name = "Name", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("Name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Show productionYear(Integer productionYear) {
    this.productionYear = productionYear;
    return this;
  }

  /**
   * Get productionYear
   * minimum: 1874
   * @return productionYear
  */
  @NotNull @Min(1874) 
  @Schema(name = "ProductionYear", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("ProductionYear")
  public Integer getProductionYear() {
    return productionYear;
  }

  public void setProductionYear(Integer productionYear) {
    this.productionYear = productionYear;
  }

  public Show seasons(Integer seasons) {
    this.seasons = seasons;
    return this;
  }

  /**
   * Get seasons
   * minimum: 1
   * maximum: 100
   * @return seasons
  */
  @NotNull @Min(1) @Max(100) 
  @Schema(name = "Seasons", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("Seasons")
  public Integer getSeasons() {
    return seasons;
  }

  public void setSeasons(Integer seasons) {
    this.seasons = seasons;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Show show = (Show) o;
    return Objects.equals(this.abstractDesc, show.abstractDesc) &&
        Objects.equals(this.ageRating, show.ageRating) &&
        Objects.equals(this.availabilityFromUtcIso, show.availabilityFromUtcIso) &&
        Objects.equals(this.backgroundUrl, show.backgroundUrl) &&
        Objects.equals(this.cast, show.cast) &&
        Objects.equals(this.category, show.category) &&
        Objects.equals(this.director, show.director) &&
        Objects.equals(this.editedAbstract, show.editedAbstract) &&
        Objects.equals(this.genre, show.genre) &&
        Objects.equals(this.id, show.id) &&
        Objects.equals(this.name, show.name) &&
        Objects.equals(this.productionYear, show.productionYear) &&
        Objects.equals(this.seasons, show.seasons);
  }

  @Override
  public int hashCode() {
    return Objects.hash(abstractDesc, ageRating, availabilityFromUtcIso, backgroundUrl, cast, category, director, editedAbstract, genre, id, name, productionYear, seasons);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Show {\n");
    sb.append("    abstractDesc: ").append(toIndentedString(abstractDesc)).append("\n");
    sb.append("    ageRating: ").append(toIndentedString(ageRating)).append("\n");
    sb.append("    availabilityFromUtcIso: ").append(toIndentedString(availabilityFromUtcIso)).append("\n");
    sb.append("    backgroundUrl: ").append(toIndentedString(backgroundUrl)).append("\n");
    sb.append("    cast: ").append(toIndentedString(cast)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    director: ").append(toIndentedString(director)).append("\n");
    sb.append("    editedAbstract: ").append(toIndentedString(editedAbstract)).append("\n");
    sb.append("    genre: ").append(toIndentedString(genre)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    productionYear: ").append(toIndentedString(productionYear)).append("\n");
    sb.append("    seasons: ").append(toIndentedString(seasons)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

