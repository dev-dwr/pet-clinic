package com.dwr.petclinic.services.map;


import com.dwr.petclinic.model.Visit;
import com.dwr.petclinic.services.VisitService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class VisitMapService extends AbstractMapService<Visit, Long> implements VisitService {
    @Override
    public Set<Visit> findAll() {
        return super.findAll();
    }

    @Override
    public Visit findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Visit save(Visit obj) {
        if(obj.getPet() == null || obj.getPet().getOwner() == null || obj.getPet().getId() == null ||
        obj.getPet().getOwner().getId() == null){
            throw new RuntimeException("Invalid visit");
        }
        return super.save(obj);
    }

    @Override
    public void delete(Visit obj) {
        super.delete(obj);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
