package hu.vereba.snippet.cm.database.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
//@Table(name = "show", schema = "public", catalog = "cmapp_service")
@DiscriminatorValue("MOVIE")
public class MovieEntity extends BaseShowEntity {


}
