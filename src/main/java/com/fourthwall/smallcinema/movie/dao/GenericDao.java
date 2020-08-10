package com.fourthwall.smallcinema.movie.dao;

import java.util.Collection;
import java.util.Optional;

public interface GenericDao<T> {

    void save(T entity);

    void saveAll(Collection<T> entities);

    Optional<T> findById(Long id);

    Iterable<T> findAll();

    void deleteAll();

    void deleteById(Long id);

}
