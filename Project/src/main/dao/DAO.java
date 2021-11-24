package main.dao;

import java.util.List;

public interface DAO<T> {

    List<T> getAll();

    void update(T ob);

    void deleteById(int id);

    void add(T ob);

    T getById(int id);
}