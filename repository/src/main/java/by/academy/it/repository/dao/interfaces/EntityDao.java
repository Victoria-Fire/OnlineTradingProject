package by.academy.it.repository.dao.interfaces;

import java.util.List;

public interface EntityDao<T> {

    T findById(Integer id);

    List<T> findAll();

    void save(T t);

    void save(List<T> list);

    void update(T t);

    void delete(Integer id);

    void closeDao();
}
