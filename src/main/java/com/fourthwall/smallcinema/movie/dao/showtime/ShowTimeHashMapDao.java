package com.fourthwall.smallcinema.movie.dao.showtime;

import com.fourthwall.smallcinema.movie.model.ShowTime;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

public class ShowTimeHashMapDao implements AbstractShowTimeDao {

    private final ConcurrentHashMap<Long, ShowTime> data = new ConcurrentHashMap<>();
    private AtomicLong nextId = new AtomicLong(1);
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock writeLock = lock.writeLock();
    private Lock readLock = lock.readLock();

    @Override
    public Set<ShowTime> getByMovieIdAndDateWithin(Long movieId, LocalDate date) {
        readLock.lock();
        try {
            return data.values().stream()
                    .filter(showTime -> showTime.getMovieId().equals(movieId))
                    .filter(showTime -> showTime.getDateTime().toLocalDate().isEqual(date))
                    .collect(Collectors.toSet());
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public void save(ShowTime entity) {
        writeLock.lock();
        try {
            if (entity.getId() == null) {
                data.put(nextId.getAndIncrement(), entity);
            } else if (!data.containsKey(entity.getId())) {
                data.put(entity.getId(), entity);
            }
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public void saveAll(Collection<ShowTime> entities) {
        entities.forEach(this::save);
    }

    @Override
    public Optional<ShowTime> findById(Long id) {
        readLock.lock();
        try {
            return Optional.ofNullable(data.get(id));
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public Iterable<ShowTime> findAll() {
        readLock.lock();
        try {
            return data.values();
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public void deleteAll() {
        writeLock.lock();
        try {
            data.clear();
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public void deleteById(Long id) {
        writeLock.lock();
        try {
            data.remove(id);
        } finally {
            writeLock.unlock();
        }
    }
}
