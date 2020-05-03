package com.dwr.petclinic.model;

import java.time.LocalDate;

public class Visit  extends BaseEntity{

    private LocalDate date;
    private String description;
    private Pet pet;

    public LocalDate getLocalDate() {
        return date;
    }

    public void setLocalDate(LocalDate localDate) {
        this.date = localDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }
}
