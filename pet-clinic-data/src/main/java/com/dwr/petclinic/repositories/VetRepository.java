package com.dwr.petclinic.repositories;

import com.dwr.petclinic.model.Vet;
import org.springframework.data.repository.CrudRepository;

public interface VetRepository  extends CrudRepository<Vet,Long> {

}
