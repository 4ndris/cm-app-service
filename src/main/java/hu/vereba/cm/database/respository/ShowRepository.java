package hu.vereba.cm.database.respository;

import hu.vereba.cm.database.entity.BaseShowEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShowRepository<T extends BaseShowEntity> extends CrudRepository<T, Long> {

    @Override
    @NotNull
    List<T> findAll();

    Optional<T> findById(String id);
}
