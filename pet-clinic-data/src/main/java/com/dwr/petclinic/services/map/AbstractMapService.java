package com.dwr.petclinic.services.map;

import com.dwr.petclinic.model.BaseEntity;

import java.util.*;

//In Map package we are creating impl of interfaces above

public abstract class AbstractMapService<T extends BaseEntity, ID extends Long> {
//ID extends Long
    protected Map<Long, T> map = new HashMap<>();

    Set<T> findAll(){
        return new HashSet<>(map.values());
    }
    T findById(ID id){
        return map.get(id);
    }
    T save(T obj){
        if(obj != null){
            if(obj.getId() == null){
                obj.setId(getNextId());
            }
            map.put(obj.getId(), obj);
        }else{
            
            throw new RuntimeException("Obj cannot be null");
        }
        return obj;
    }
    private Long getNextId(){
        Long nextId = null;
        try{
            nextId = Collections.max(map.keySet()) +1;
        }catch (NoSuchElementException e){
            nextId = 1L;
        }

        return nextId;
    }
    void deleteById(ID id){
        map.remove(id);
    }
    void delete(T obj){
        //Returns a Set view of the mappings contained in this map.
        map.entrySet().removeIf(entry -> entry.getValue().equals(obj));
    }

}
