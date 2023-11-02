package hu.vereba.cm.database.entity;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("MOVIE")
public class MovieEntity extends BaseShowEntity {


}
