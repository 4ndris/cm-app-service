package hu.vereba.cm.service;

import hu.vereba.cm.database.entity.BaseShowEntity;
import hu.vereba.cm.database.entity.MovieEntity;
import hu.vereba.cm.database.entity.SeriesEntity;
import hu.vereba.cm.database.respository.ShowRepository;
import hu.vereba.cm.exception.DuplicateShowIdException;
import hu.vereba.cm.exception.InvalidCategoryException;
import hu.vereba.cm.exception.ShowNotFoundException;
import hu.vereba.cm.mapper.ShowMapper;
import hu.vereba.cm.rest.model.Show;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShowService {

    private final ShowRepository<BaseShowEntity> showRepository;
    private final ShowMapper showMapper;

    @Autowired
    public ShowService(ShowRepository<BaseShowEntity> showRepository, ShowMapper showMapper) {

        this.showRepository = showRepository;
        this.showMapper = showMapper;
    }

    public List<Show> getShowList() {
        List<? extends BaseShowEntity> showEntities = showRepository.findAll();
        return showEntities.stream()
                .map(showMapper::entityToShow)
                .collect(Collectors.toList());
    }

    // CRUD operations

    @Transactional
    public void addShow(Show show) {
        Optional<? extends BaseShowEntity> existingShow = showRepository.findById(show.getId());
        if(existingShow.isPresent()) {
            throw new DuplicateShowIdException(show.getId());
        }

        switch (show.getCategory()) {
            case MOVIE:
                MovieEntity movieEntity = showMapper.showToMovieEntity(show);
                showRepository.save(movieEntity);
                break;
            case SERIES:
                SeriesEntity seriesEntity = showMapper.showToSeriesEntity(show);
                showRepository.save(seriesEntity);
                break;
            default:
                throw new InvalidCategoryException("Invalid category: " + show.getCategory());
        }
    }

    public Show getShow(String id) {
        BaseShowEntity showEntity = showRepository.findById(id).orElseThrow(() -> new ShowNotFoundException(id));
        return showMapper.entityToShow(showEntity);
    }

    @Transactional
    public Show updateShow(String id, Show show) {
        var showEntity = showRepository.findById(id).orElseThrow(() -> new ShowNotFoundException(id));
        showEntity = showMapper.showToEntity(show);
        return showMapper.entityToShow(showEntity);
    }

    @Transactional
    public void deleteShow(String id) {
        BaseShowEntity showEntity = showRepository.findById(id).orElseThrow(() -> new ShowNotFoundException(id));
        showRepository.delete(showEntity);
    }
}
