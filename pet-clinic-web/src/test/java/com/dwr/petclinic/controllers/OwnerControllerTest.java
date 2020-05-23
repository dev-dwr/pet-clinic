package com.dwr.petclinic.controllers;

import com.dwr.petclinic.model.Owner;
import com.dwr.petclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.hamcrest.Matchers.*;

import java.util.HashSet;
import java.util.Set;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {
    @Mock
    OwnerService ownerService;

    @InjectMocks
    OwnerController ownerController;

    Set<Owner> owners;

    MockMvc mockMvc; //set up controller to testing

    @BeforeEach
    void setUp() {
        owners = new HashSet<>();
        owners.add(Owner.builder().id(1L).build());
        owners.add(Owner.builder().id(2L).build());

        mockMvc = MockMvcBuilders
                .standaloneSetup(ownerController)
                .build();
    }

    @Test
    void listOwners() {
        when(ownerService.findAll()).thenReturn(owners);
        try {
            mockMvc.perform(get("/owners"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("owners/owners.html"))
                    .andExpect(model().attribute("owners", hasSize(2)));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    void listOwnersByHtmlTag() {
        when(ownerService.findAll()).thenReturn(owners);
        try {
            mockMvc.perform(get("/owners/owners.html"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("owners/owners.html"))
                    .andExpect(model().attribute("owners", hasSize(2)));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    void findOwners() throws Exception {

        mockMvc.perform(get("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("notimplemented.html"));

    }
    @Test
    void displayOwners() throws Exception{
        when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(1L).build());
        mockMvc.perform(get("/owners/1"))
                .andExpect(view().name("owners/ownerDetails"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("owner", hasProperty("id", is(1L))));
    }
}