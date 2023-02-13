package com.ali.Utility;

import java.util.List;
import java.util.Optional;

public interface ICrud<T, ID> extends IMyRepository<T,ID> {
    <S extends T> S save(S entity);
    <S extends  T> Iterable<S> saveAll(Iterable<S> entities);
    void delete(T entity);
    void deleteById(ID id);
    Optional<T> findById(ID id);
    boolean existsById(ID id);
    List<T> findAll();
    List<T> findByEntity(T entity);
    List<T> findAllByColumnNameAndValue(String columnName, String columnValue);
}
