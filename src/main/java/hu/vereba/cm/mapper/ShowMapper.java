package hu.vereba.cm.mapper;

import hu.vereba.cm.database.entity.BaseShowEntity;
import hu.vereba.cm.database.entity.MovieEntity;
import hu.vereba.cm.database.entity.SeriesEntity;
import hu.vereba.cm.rest.model.Show;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.SubclassMapping;

@Mapper
public interface ShowMapper {

    @Mapping(target = "seasons", ignore = true)
    @SubclassMapping(source = SeriesEntity.class, target = Show.class)
    @SubclassMapping(source = MovieEntity.class, target = Show.class)
    Show entityToShow(BaseShowEntity entity);

    @AfterMapping
    default void addSeasonsOptinally(BaseShowEntity entity, @MappingTarget Show show) {
        if(entity instanceof SeriesEntity) {
            show.setSeasons(((SeriesEntity)entity).getSeasons());
        }
    }

    @SuppressWarnings("unchecked") 
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
