package hu.vereba.snippet.cm.service;

import hu.vereba.snippet.cm.database.entity.BaseShowEntity;
import hu.vereba.snippet.cm.database.entity.MovieEntity;
import hu.vereba.snippet.cm.database.entity.SeriesEntity;
import hu.vereba.snippet.cm.database.respository.ShowRepository;
import hu.vereba.snippet.cm.exception.DuplicateShowIdException;
import hu.vereba.snippet.cm.exception.ShowNotFoundException;
import hu.vereba.snippet.cm.rest.model.Show;
import org.modelmapper.ModelMapper;
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
            case "MOVIE":
                MovieEntity movieEntity = modelMapper.map(show, MovieEntity.class);
                showRepository.save(movieEntity);
                break;
            case "SERIES":
                SeriesEntity seriesEntity = modelMapper.map(show, SeriesEntity.class);
                showRepository.save(seriesEntity);
                break;
            default:
        }


    }

    public Show getShow(String id) {
        BaseShowEntity movieEntity = showRepository.findById(id).orElseThrow(() -> new ShowNotFoundException(id));
        return modelMapper.map(movieEntity, Show.class);
    }

    @Transactional
    public Show updateShow(String id, Show show) {
        BaseShowEntity movieEntity = showRepository.findById(id).orElseThrow(() -> new ShowNotFoundException(id));
        modelMapper.map(show, movieEntity);
        return modelMapper.map(movieEntity, Show.class);
    }

    @Transactional
    public void deleteShow(String id) {
        BaseShowEntity movieEntity = showRepository.findById(id).orElseThrow(() -> new ShowNotFoundException(id));
        showRepository.delete(movieEntity);
    }
}
