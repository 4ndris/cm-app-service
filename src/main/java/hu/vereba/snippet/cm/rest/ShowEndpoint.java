package hu.vereba.snippet.cm.rest;

import hu.vereba.snippet.cm.rest.model.ErrorMessage;
import hu.vereba.snippet.cm.rest.model.Show;
import hu.vereba.snippet.cm.rest.model.Shows;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(allowCredentials = "true", origins = "localhost")
@Validated
public interface ShowEndpoint {

    String URL_ROOT = "/";
    String URL_SHOWS = "/services/shows";
    String URL_SHOW_BY_ID = URL_SHOWS + "/{id}";

    @Operation(summary = "Welcome page", description = "", tags={ "show" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = String.class))) })
    @GetMapping(path = URL_ROOT, produces = { "text/plain" })
    default String welcome() {
        return "Welcome - Content Manager Service";
    }

    @Operation(summary = "Get the list of shows (movies and series)", description = "", tags={ "show" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Shows.class))) })
    @GetMapping(path = URL_SHOWS, produces = { "application/json" })
    ResponseEntity<Shows> listShows();

    @Operation(summary = "Create a new show (movie or series)", description = "", tags={ "show" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Show created"),
            @ApiResponse(responseCode = "400", description = "Invalid request sent"),
            @ApiResponse(responseCode = "409", description = "Id already exist among items", content = @Content(schema = @Schema(implementation = ErrorMessage.class))) })
    @PostMapping(path = URL_SHOWS,
            produces = { "application/json" },
            consumes = { "application/json" })
    ResponseEntity<Void> addShow(@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody Show show);

    @Operation(summary = "Get show with a specific ID", description = "", tags={ "show" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Show.class))),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Show not found"),
            @ApiResponse(responseCode = "405", description = "Validation exception") })
    @GetMapping(path = URL_SHOW_BY_ID, produces = { "application/json" })
    ResponseEntity<Show> getShow(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable String id);

    @Operation(summary = "Update show for a given ID", description = "", tags={ "show" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Show.class))),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Show not found"),
            @ApiResponse(responseCode = "405", description = "Validation exception") })
    @PutMapping(path = URL_SHOW_BY_ID)
    ResponseEntity<Show> updateShow(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable String id, @Valid @RequestBody Show show);

    @Operation(summary = "Delete show with a specific ID", description = "", tags={ "show" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Show not found"),
            @ApiResponse(responseCode = "405", description = "Validation exception") })
    @DeleteMapping(path = URL_SHOW_BY_ID)
    ResponseEntity<Void> deleteShow(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable String id);
}
