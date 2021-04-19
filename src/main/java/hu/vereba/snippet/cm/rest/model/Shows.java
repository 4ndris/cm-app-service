package hu.vereba.snippet.cm.rest.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Items"
})
public class Shows {

    @JsonProperty("Items")
    private List<Show> items = null;

    public List<Show> getItems() {
        return items;
    }

    public void setItems(List<Show> items) {
        this.items = items;
    }

}