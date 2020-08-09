package com.fourthwall.smallcinema.movie.dao;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface AbstractH2Dao<T> extends GenericDao<T> {

    CrudRepository<T, Long> getCrudRepository();

    default void save(T entity) {
        getCrudRepository().save(entity);
    }

    default void saveAll(Collection<T> entities) {
        getCrudRepository().saveAll(entities);
    }

    default Optional<T> findById(Long id) {
        return getCrudRepository().findById(id);
    }

    default Iterable<T> findAll() {
        return getCrudRepository().findAll();
    }

    default void deleteAll() {
        getCrudRepository().deleteAll();
    }

    default void deleteById(Long id) {
        getCrudRepository().findById(id);
    }

}
