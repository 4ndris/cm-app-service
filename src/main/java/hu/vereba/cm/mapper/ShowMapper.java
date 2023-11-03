package hu.vereba.cm.mapper;

import hu.vereba.cm.database.entity.BaseShowEntity;
import hu.vereba.cm.database.entity.MovieEntity;
import hu.vereba.cm.database.entity.SeriesEntity;
import hu.vereba.cm.rest.model.Show;
import org.mapstruct.*;

@Mapper
public interface ShowMapper {
    @Mapping(target = "seasons", source = "seasons")
    Show entityToShow(SeriesEntity entity);

    @Mapping(target = "seasons", ignore = true)
    Show entityToShow(MovieEntity entity);

    default Show entityToShow(BaseShowEntity entity) {
        if (entity instanceof SeriesEntity) {
            return entityToShow((SeriesEntity) entity);
        } else if (entity instanceof MovieEntity) {
            return entityToShow((MovieEntity) entity);
        } else {
            throw new IllegalArgumentException("Unsupported entity type");
        }
    }

    default <T extends BaseShowEntity> T showToEntity(Show show) {
        if(show.getCategory() == Show.CategoryEnum.SERIES) {
            return (T) showToSeriesEntity(show);
        } else if (show.getCategory() == Show.CategoryEnum.MOVIE) {
            return (T) showToMovieEntity(show);
        } else {
            throw new IllegalArgumentException("Unsupported show category");
        }
    }

    @Mapping(target = "itemId", ignore = true) // Let the JPA handle the ID generation
    @Mapping(target = "category", source = "category.value") // Map category enum value
    MovieEntity showToMovieEntity(Show show);

    @Mapping(target = "itemId", ignore = true) // Let the JPA handle the ID generation
    @Mapping(target = "category", source = "category.value") // Map category enum value
    SeriesEntity showToSeriesEntity(Show show);
}
