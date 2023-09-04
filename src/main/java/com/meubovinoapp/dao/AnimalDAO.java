package com.meubovinoapp.dao;

import com.meubovinoapp.POJO.Animal;
import com.meubovinoapp.wrapper.AnimalWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AnimalDAO extends JpaRepository<Animal, Integer> {

    Animal findAnimalById(@Param("id") Integer id);

    List<AnimalWrapper> getAllAnimal();

    Animal findAnimalByName(@Param("name") String name);
}
