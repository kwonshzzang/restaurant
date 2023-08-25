package kr.co.kwonshzzang.restaurant.db;

import java.util.Optional;
import java.util.List;

public interface MemoryDbRepositoryIfs<T> {
    Optional<T> findById(Long id);
    T save(T entity);
    void deleteById(Long id);
    List<T> findAll();
}
