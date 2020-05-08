package com.dwr.petclinic.repositories;

import com.dwr.petclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository  extends CrudRepository<Owner, Long> {
}
