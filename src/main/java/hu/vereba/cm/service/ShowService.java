package hu.vereba.cm.service;

import hu.vereba.cm.database.document.ShowDocument;
import hu.vereba.cm.database.repository.ShowRepository;
import hu.vereba.cm.exception.DuplicateShowIdException;
import hu.vereba.cm.exception.ShowNotFoundException;
import hu.vereba.cm.mapper.ShowMapper;
import hu.vereba.cm.rest.model.Show;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShowService {

    private final ShowRepository showRepository;
    private final ShowMapper showMapper;

    @Autowired
    public ShowService(ShowRepository showRepository, ShowMapper showMapper) {
        this.showRepository = showRepository;
        this.showMapper = showMapper;
    }

    public List<Show> getShowList() {
        List<ShowDocument> showDocuments = showRepository.findAll();
        return showDocuments.stream()
                .map(showMapper::documentToShow)
                .collect(Collectors.toList());
    }

    public void addShow(Show show) {
        Optional<ShowDocument> existingShow = showRepository.findById(show.getId());
        if (existingShow.isPresent()) {
            throw new DuplicateShowIdException(show.getId());
        }

        ShowDocument showDocument = showMapper.showToDocument(show);
        showRepository.save(showDocument);
    }

    public Show getShow(String imdbId) {
        ShowDocument showDocument = showRepository.findShowDocumentByImdbId(imdbId)
                .orElseThrow(() -> new ShowNotFoundException(imdbId));
        return showMapper.documentToShow(showDocument);
    }

    public Show updateShow(String imdbId, Show show) {
        ShowDocument existingShow = showRepository.findShowDocumentByImdbId(imdbId)
                .orElseThrow(() -> new ShowNotFoundException(imdbId));

        showMapper.showToDocument(show, existingShow);

        showRepository.save(existingShow);
        return showMapper.documentToShow(existingShow);
    }

    public void deleteShow(String imdbId) {
        ShowDocument showDocument = showRepository.findShowDocumentByImdbId(imdbId)
                .orElseThrow(() -> new ShowNotFoundException(imdbId));
        showRepository.delete(showDocument);
    }
}
