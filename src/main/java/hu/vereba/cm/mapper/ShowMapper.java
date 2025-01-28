package hu.vereba.cm.mapper;

import hu.vereba.cm.database.document.ShowDocument;
import hu.vereba.cm.rest.model.Show;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ShowMapper {

    @Mapping(source = "category", target = "category", qualifiedByName = "stringToCategory")
    @Mapping(source = "imdbId", target = "id")
    Show documentToShow(ShowDocument document);

    @Mapping(source = "category", target = "category", qualifiedByName = "categoryToString")
    @Mapping(source = "id", target = "imdbId")
    @Mapping(target = "id", ignore = true)
    ShowDocument showToDocument(Show show);

    @Mapping(source = "category", target = "category", qualifiedByName = "categoryToString")
    @Mapping(source = "id", target = "imdbId")
    @Mapping(target = "id", ignore = true)
    ShowDocument showToDocument(Show show, @MappingTarget ShowDocument document);

    // Custom mapping for the category field
    @Named("stringToCategory")
    default Show.CategoryEnum stringToCategory(String category) {
        if (category == null) {
            return null;
        }
        return switch (category.toUpperCase()) {
            case "MOVIE" -> Show.CategoryEnum.MOVIE;
            case "SERIES" -> Show.CategoryEnum.SERIES;
            default -> throw new IllegalArgumentException("Unknown category: " + category);
        };
    }

    @Named("categoryToString")
    default String categoryToString(Show.CategoryEnum category) {
        return category != null ? category.name() : null;
    }
}
