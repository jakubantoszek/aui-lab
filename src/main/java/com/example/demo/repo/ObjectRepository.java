package com.example.demo.repo;

import java.util.ArrayList;
import java.util.Optional;

public interface ObjectRepository<T, U> {
    void add(T t);

    Optional<T> findByKey(U u);

    ArrayList<T> findAll();

    void delete(U u);
}
