package hu.vereba.cm.database.respository;

import hu.vereba.cm.database.entity.BaseShowEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ShowRepository<T extends BaseShowEntity> extends CrudRepository<T, Long> {

    @Override
    List<T> findAll();

    Optional<T> findById(String id);
}
