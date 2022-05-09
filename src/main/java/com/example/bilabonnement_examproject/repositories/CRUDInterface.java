package com.example.bilabonnement_examproject.repositories;

import java.util.List;

public interface CRUDInterface <T>{

    //read all entities
    public List<T> getAllEntities();

    //read single entity
    public T getSingleEntity(int id);

    //create entity
    public boolean createEntity(T entity);

    //update entity
    public boolean updateEntity(int id);

}
