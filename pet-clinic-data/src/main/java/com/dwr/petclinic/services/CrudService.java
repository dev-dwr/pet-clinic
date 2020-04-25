package com.dwr.petclinic.services;

import java.util.Set;
//type, id
public interface CrudService<T, ID> {

    Set<T> findAll();

    T findById(ID id);

    T save(T obj);

    void delete(T obj);

    void deleteById(ID id);

}
