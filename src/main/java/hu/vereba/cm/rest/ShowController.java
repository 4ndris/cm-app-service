package hu.vereba.cm.rest;

import hu.vereba.cm.rest.api.ShowApi;
import hu.vereba.cm.rest.model.Show;
import hu.vereba.cm.rest.model.Shows;
import hu.vereba.cm.service.ShowService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class ShowController implements ShowApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShowController.class);

    private final ShowService showService;

    @Autowired
    public ShowController(ShowService showService) {
        this.showService = showService;
    }

    @Override
    public ResponseEntity<Shows> listShows() {
        LOGGER.debug("requesting list of shows");
        List<Show> shows = showService.getShowList();
        Shows items = new Shows();
        items.setItems(shows);
        LOGGER.debug("sending back (number of) shows: {}", shows.size());
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> addShow(@Valid Show show) {
        LOGGER.debug("adding show: {}", show);
        showService.addShow(show);
        LOGGER.debug("show added");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Show> getShow(String id) {
        LOGGER.debug("requesting show with id: {}", id);
        Show show = showService.getShow(id);
        LOGGER.debug("Show retrieved: {}", show);
        return new ResponseEntity<>(show, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Show> updateShow(String id, @Valid Show show) {
        LOGGER.debug("updating show with id: {}, show: {}", id, show);
        Show updatedShow = showService.updateShow(id, show);
        LOGGER.debug("updated show: {}",  updatedShow);
        return new ResponseEntity<>(updatedShow, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> welcome() {
        return ResponseEntity.ok("Welcome - Content Manager Service");
    }

    @Override
    public ResponseEntity<Void> deleteShow(String id) {
        LOGGER.debug("deleting show with id: {}", id);
        showService.deleteShow(id);
        LOGGER.debug("show deleted with id: {}", id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
