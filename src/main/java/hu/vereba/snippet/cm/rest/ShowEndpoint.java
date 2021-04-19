package hu.vereba.snippet.cm.rest;

import hu.vereba.snippet.cm.rest.model.ErrorMessage;
import hu.vereba.snippet.cm.rest.model.Show;
import hu.vereba.snippet.cm.rest.model.Shows;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(allowCredentials = "true")
@Api(value = "shows")
public interface ShowEndpoint {

    String URL_ROOT = "/";
    String URL_SHOWS = "/services/shows";
    String URL_SHOW_BY_ID = URL_SHOWS + "/{id}";

    @GetMapping(path = URL_ROOT)
    @ApiOperation(value = "Welcome message", tags = {"show"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation")})
    @ResponseBody
    default String welcome() {
        return "Welcome - Content Manager Service";
    }

    @ApiOperation(value = "List all shows",
            notes = "Provides the whole list of stored items",
            response = Shows.class, tags={"show"})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "successful operation", response = Shows.class)})
    @GetMapping(path = URL_SHOWS)
    ResponseEntity<Shows> listShows();

    @ApiOperation(value = "Add a new show to the list of items", notes = "", tags={"show"})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Show created"),
            @ApiResponse(code = 409, message = "Id already exist among items", response = ErrorMessage.class),
            @ApiResponse(code = 400, message = "Invalid request sent")
            })
    @PostMapping(path = URL_SHOWS)
    ResponseEntity<Void> addShow(@Valid @RequestBody Show show);

    @ApiOperation(value = "Find show by Id", notes = "", tags={"show"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation", response = Show.class),
            @ApiResponse(code = 404, message = "Show not found")
    })
    @GetMapping(path = URL_SHOW_BY_ID)
    ResponseEntity<Show> getShow(@PathVariable String id);

    @ApiOperation(value = "Update and existing show", notes = "", tags={"show"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation", response = Show.class),
            @ApiResponse(code = 400, message = "Invalid show supplied"),
            @ApiResponse(code = 404, message = "Show not found")
    })
    @PutMapping(path = URL_SHOW_BY_ID)
    ResponseEntity<Show> updateShow(@PathVariable String id, @Valid @RequestBody Show show);

    @ApiOperation(value = "Delete a show by Id", notes = "", tags={"show"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 404, message = "Show not found")
    })
    @DeleteMapping(path = URL_SHOW_BY_ID)
    ResponseEntity<Void> deleteShow(@PathVariable String id);
}
