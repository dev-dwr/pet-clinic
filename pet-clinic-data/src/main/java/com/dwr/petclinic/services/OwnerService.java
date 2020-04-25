package com.dwr.petclinic.services;

import com.dwr.petclinic.model.Owner;


public interface OwnerService extends CrudService<Owner, Long>{

    Owner findByLastName(String lastName);

}
