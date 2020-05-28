package com.dwr.petclinic.services.map;

import com.dwr.petclinic.model.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PetMapServiceTest {
    private PetMapService petMapService;

    @BeforeEach
    void setUp() {
        petMapService = new PetMapService();

        petMapService.save(Pet.builder().id(1L).build());
    }

    @Test
    void findAll() {
        Set<Pet> petSet = petMapService.findAll();

        assertEquals(1, petSet.size());
    }

    @Test
    void findById() {
        Pet pet = petMapService.findById(1L);

        assertEquals(1L,pet.getId());
    }

    @Test
    void findByNoExistingId(){
        Pet pet = petMapService.findById(1000L);
        assertNull(pet);
    }
    @Test
    void findByNullId(){
        Pet pet = petMapService.findById(null);

        assertNull(pet);
    }
    @Test
    void save() {
        Pet pet2 = Pet.builder().id(2L).build();

        Pet savedPet = petMapService.save(pet2);

        assertEquals(2L, savedPet.getId());
    }

    @Test
    void saveDuplicatedId(){
        Pet pet1 = Pet.builder().id(1L).build();
        Pet savedPet1 = petMapService.save(pet1);

        assertEquals(1L, savedPet1.getId());
        assertEquals(1, petMapService.findAll().size());
    }

    @Test
    void saveNoId(){
        Pet savedPet = petMapService.save(Pet.builder().build());

        assertNotNull(savedPet);
        assertNotNull(savedPet.getId());
        assertEquals(2, petMapService.findAll().size());
    }

    @Test
    void deleteByObj() {
        petMapService.delete(petMapService.findById(1L));
        assertEquals(0, petMapService.findAll().size());

    }

    @Test
    void deleteNull() {

        petMapService.delete(null);

        assertEquals(1, petMapService.findAll().size());

    }

    @Test
    void deleteById() {
        petMapService.deleteById(1L);

        assertEquals(0, petMapService.findAll().size());
    }
}