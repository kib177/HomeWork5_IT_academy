package by.HomeWork.storage.api;

import java.util.List;

public interface IRepository<T> {
    void save(T entity);
    List<T> getAll();

}
