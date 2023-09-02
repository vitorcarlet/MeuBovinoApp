package com.meubovinoapp.dao;

import com.meubovinoapp.POJO.Animal;

import java.util.List;

public interface AnimalDAO {
    List<Object> findById(Integer animalId);

    void save(Animal animal);
}
