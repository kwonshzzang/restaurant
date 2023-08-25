package kr.co.kwonshzzang.restaurant.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public abstract class MemoryDbRepositoryAbstract<T extends MemoryDbEntity> implements MemoryDbRepositoryIfs<T> {
    private final List<T> db = new ArrayList<>();
    private Long id = 0L;


    @Override
    public Optional<T> findById(Long id) {
        return db.stream().filter(it -> Objects.equals(it.getId(), id)).findFirst();
    }

    @Override
    public T save(T entity) {
        Optional<T> optionalEntity = findById(entity.getId());
        if(optionalEntity.isEmpty()) {
            //insert
            id++;
            entity.setId(id);
            db.add(entity);
        } else {
            //update
            var preId = optionalEntity.get().getId();
            entity.setId(preId);
            deleteById(preId);
            db.add(entity);
        }
        return entity;
    }

    @Override
    public void deleteById(Long id) {
        Optional<T> optionalEntity = findById(id);
        optionalEntity.ifPresent(db::remove);
    }

    @Override
    public List<T> findAll() {
        return db;
    }
}
