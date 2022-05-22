package com.example.bilabonnement_examproject.repositories;

import com.example.bilabonnement_examproject.models.CarModel;

import java.util.List;

public interface CRUDInterface <T, ID>{

    //read all entities
    public List<T> getAllEntities();

    //read single entity
    public T getSingleEntity(ID id);

    //create entity
    public boolean createEntity(T entity);

    //update entity
    public boolean updateEntity(T entity);

}
