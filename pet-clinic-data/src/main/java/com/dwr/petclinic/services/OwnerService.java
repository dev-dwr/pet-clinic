package com.dwr.petclinic.services;

import com.dwr.petclinic.model.Owner;

import java.util.List;


public interface OwnerService extends CrudService<Owner, Long>{
    List<Owner> findAllByLastNameLike(String lastName);
    Owner findByLastName(String lastName);

}
