package hu.vereba.cm.service;

import hu.vereba.cm.database.entity.BaseShowEntity;
import hu.vereba.cm.database.entity.MovieEntity;
import hu.vereba.cm.database.entity.SeriesEntity;
import hu.vereba.cm.database.respository.ShowRepository;
import hu.vereba.cm.exception.DuplicateShowIdException;
import hu.vereba.cm.exception.InvalidCategoryException;
import hu.vereba.cm.exception.ShowNotFoundException;
import hu.vereba.cm.rest.model.Show;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShowService {

    private final ShowRepository<BaseShowEntity> showRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ShowService(ShowRepository showRepository, ModelMapper modelMapper) {

        this.showRepository = showRepository;
        this.modelMapper = modelMapper;
    }

    public List<Show> getShowList() {
        List<? extends BaseShowEntity> showEntities = showRepository.findAll();
        return showEntities.stream()
                .map(showEntity -> modelMapper.map(showEntity, Show.class))
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
                MovieEntity movieEntity = modelMapper.map(show, MovieEntity.class);
                showRepository.save(movieEntity);
                break;
            case SERIES:
                SeriesEntity seriesEntity = modelMapper.map(show, SeriesEntity.class);
                showRepository.save(seriesEntity);
                break;
            default:
                throw new InvalidCategoryException("Invalid category: " + show.getCategory());
        }
    }

    public Show getShow(String id) {
        BaseShowEntity showEntity = showRepository.findById(id).orElseThrow(() -> new ShowNotFoundException(id));
        return modelMapper.map(showEntity, Show.class);
    }

    @Transactional
    public Show updateShow(String id, Show show) {
        BaseShowEntity showEntity = showRepository.findById(id).orElseThrow(() -> new ShowNotFoundException(id));
        modelMapper.map(show, showEntity);
        return modelMapper.map(showEntity, Show.class);
    }

    @Transactional
    public void deleteShow(String id) {
        BaseShowEntity showEntity = showRepository.findById(id).orElseThrow(() -> new ShowNotFoundException(id));
        showRepository.delete(showEntity);
    }
}
