package com.example.demo;

import java.util.ArrayList;

public interface ObjectRepository<T> {
    void add(T t);

    ArrayList<T> getAllObjects();
}
