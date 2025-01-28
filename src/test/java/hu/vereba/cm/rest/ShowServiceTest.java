package hu.vereba.cm.rest;

import hu.vereba.cm.config.MongoContainerConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Import(MongoContainerConfig.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ShowServiceTest {
    
    private final static String URL_SHOWS = "/services/shows";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getShows() throws Exception {
        //GIVEN
        //WHEN
        this.mockMvc.perform(
                get(URL_SHOWS).accept("application/json;charset=UTF-8"))
                .andDo(print())
                //THEN
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.items", is(not(emptyArray()))));
    }

    @Test
    void createShowShallSuccessful() throws Exception {
        //GIVEN
        byte[] jsonContent = getContent("series/show_controller_create_valid.json");

        //WHEN
        mockMvc.perform(
                post(URL_SHOWS)
                        .content(jsonContent)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                //THEN
                .andExpect(status().isCreated());
    }

    @Test
    void createShowShallFailWithDuplicateId() throws Exception {
        //GIVEN
        byte[] jsonContent = getContent("series/show_controller_create_valid.json");

        //WHEN
        mockMvc.perform(
                post(URL_SHOWS)
                        .content(jsonContent)
                        .contentType(MediaType.APPLICATION_JSON)
                );
        mockMvc.perform(
                post(URL_SHOWS)
                        .content(jsonContent)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                //THEN
                .andExpect(status().isConflict());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "series/show_controller_create_inc.json",
            "series/show_controller_create_invalid_rating.json",
            "series/show_controller_create_invalid_prod_year.json"})
    void createShowShallFailWithInvalidContent(String path) throws Exception {
        //GIVEN
        byte[] jsonContent = getContent(path);

        //WHEN
        mockMvc.perform(
                post(URL_SHOWS)
                        .content(jsonContent)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                //THEN
                .andExpect(status().isBadRequest());
    }

    @Test
    void getShowShallSucceed() throws Exception {
        //GIVEN
        String id = "tt4998350";

        //WHEN
        mockMvc.perform(
                get(URL_SHOWS + "/" + id)
                        .accept("application/json;charset=UTF-8"))
                .andDo(print())
                //THEN
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Director", is("Michelle Maclaren")));
    }

    @Test
    void getShowShallFailWithNotExist() throws Exception {
        //GIVEN
        String id = "d7e365d8-3e5d-456f-afa5-2c8acb6144ff";

        //WHEN
        mockMvc.perform(
                get(URL_SHOWS + "/" + id)
                        .accept("application/json;charset=UTF-8"))
                .andDo(print())
                //THEN
                .andExpect(status().isNotFound());
    }

    @Test
    void updateShowShallSuccessful() throws Exception {
        //GIVEN
        byte[] jsonContent = getContent("series/show_controller_update_valid.json");
        String id = "tt8103070";

        //WHEN
        mockMvc.perform(
                put(URL_SHOWS + "/" + id)
                        .content(jsonContent)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept("application/json;charset=UTF-8")
        )
                .andDo(print())
                //THEN
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.AgeRating", is(18)));
    }

    @Test
    void updateShowShallShallFailWithIncompleteRequest() throws Exception {
        //GIVEN
        byte[] jsonContent = getContent("series/show_controller_update_inc.json");
        String id = "tt8103070";

        //WHEN
        mockMvc.perform(
                put(URL_SHOWS + "/" + id)
                        .content(jsonContent)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept("application/json;charset=UTF-8")
        )
                .andDo(print())
                //THEN
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateShowShallShallFailWithNonExistingShow() throws Exception {
        //GIVEN
        byte[] jsonContent = getContent("series/show_controller_update_valid.json");
        String id = "ccfad5bd-716d-9f55-0f0d-cd6f71864c5b";

        //WHEN
        mockMvc.perform(
                put(URL_SHOWS + "/" + id)
                        .content(jsonContent)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept("application/json;charset=UTF-8")
        )
                .andDo(print())
                //THEN
                .andExpect(status().isNotFound());
    }

    @Test
    void updateShowShallShallFailWithInvalidContent() throws Exception {
        //GIVEN
        byte[] jsonContent = getContent("series/show_controller_update_invalid.json");
        String id = "tt8103070";

        //WHEN
        mockMvc.perform(
                put(URL_SHOWS + "/" + id)
                        .content(jsonContent)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept("application/json;charset=UTF-8")
        )
                .andDo(print())
                //THEN
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteShowShallSucceed() throws Exception {
        //GIVEN
        String id = "tt8103070";

        //WHEN
        mockMvc.perform(
                delete(URL_SHOWS + "/" + id)
                        .accept("application/json;charset=UTF-8"))
                .andDo(print())
                //THEN
                .andExpect(status().isOk());
    }

    @Test
    void deleteShowShallFailWithNotExist() throws Exception {
        //GIVEN
        String id = "d7e365d8-3e5d-456f-afa5-2c8acb6144ff";

        //WHEN
        mockMvc.perform(
                delete(URL_SHOWS + "/" + id)
                        .accept("application/json;charset=UTF-8"))
                .andDo(print())
                //THEN
                .andExpect(status().isNotFound());
    }


    private byte[] getContent(String file) throws IOException {
        Path resourceDirectory = Paths.get("src","test","resources", "test_requests", file);
        return Files.readAllBytes(resourceDirectory);
    }

}
