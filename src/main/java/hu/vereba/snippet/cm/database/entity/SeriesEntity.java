package hu.vereba.snippet.cm.database.entity;

import javax.persistence.*;

@Entity
//@Table(name = "show", schema = "public", catalog = "cmapp_service")
@DiscriminatorValue("SERIES")
public class SeriesEntity extends BaseShowEntity {

    private int seasons;

    public int getSeasons() {
        return seasons;
    }

    public void setSeasons(int seasons) {
        this.seasons = seasons;
    }
}
