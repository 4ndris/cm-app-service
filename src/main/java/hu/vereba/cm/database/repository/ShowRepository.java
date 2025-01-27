package hu.vereba.cm.database.repository;

import hu.vereba.cm.database.document.ShowDocument;
import jakarta.annotation.Nonnull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShowRepository extends MongoRepository<ShowDocument, String> {

    @Override
    @Nonnull
    List<ShowDocument> findAll();

    @Nonnull
    Optional<ShowDocument> findById(@Nonnull String id);

    // Add custom query methods if needed
    List<ShowDocument> findByCategory(String category); // Example: Find shows by category (MOVIE or SERIES)
}
