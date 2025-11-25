package com.lms.dao;

import java.util.List;
import java.util.Optional;

public interface BaseDao<T, ID> {
    T save(T t);
    T update(T t);
    void delete(ID id);
    Optional<T> findById(ID id);
    List<T> findAll();
}
