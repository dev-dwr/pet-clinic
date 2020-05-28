package com.dwr.petclinic.controllers;

import com.dwr.petclinic.model.Owner;
import com.dwr.petclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


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
    void findOwners() throws Exception {
        mockMvc.perform(get("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/findOwners"))
                .andExpect(model().attributeExists("owner"));
    }

    @Test
    void processFindOwnersReturnedOne() throws Exception {
        when(ownerService.findAllByLastNameLike(anyString()))
                .thenReturn(Arrays.asList(Owner.builder().id(1L).build()));

        mockMvc.perform(get("/owners"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));
    }

    @Test
    void processFindFormReturnMany() throws Exception {
        when(ownerService.findAllByLastNameLike(anyString()))
                .thenReturn(Arrays.asList(Owner.builder().id(1L).build(),
                        Owner.builder().id(2L).build()));

        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownersList"))
                .andExpect(model().attribute("selections", hasSize(2)));
    }


    @Test
    void displayOwners() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(2L).build());
        mockMvc.perform(get("/owners/2"))
                .andExpect(view().name("owners/ownerDetails"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("owner", hasProperty("id", is(2L))));
    }

    @Test
    void initCreationFormTest() throws Exception{
        mockMvc.perform(get("/owners/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/createOrUpdateOwnerForm"))
                .andExpect(model().attributeExists("owner"));
    }
    @Test
    void processCreationFormTest() throws Exception{
        //ArgumentMatchers.any() matches anything including null
        when(ownerService.save(any())).thenReturn(Owner.builder().id(1L).build());

        mockMvc.perform(post("/owners/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"))
                .andExpect(model().attributeExists("owner"));
        verify(ownerService, times(1)).save(any());
    }

    @Test
    void initUpdateOwnerForm() throws Exception{
        when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(1L).build());

        mockMvc.perform(get("/owners/1/edit"))
                .andExpect(model().attributeExists("owner"))
                .andExpect(view().name("owners/createOrUpdateOwnerForm"))
                .andExpect(status().isOk());
        verify(ownerService,times(1)).findById(anyLong());
    }
    @Test
    void processUpdateOwnerFormTest() throws Exception{
        when(ownerService.save(any())).thenReturn(Owner.builder().id(1L).build());

        mockMvc.perform(post("/owners/1/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"))
                .andExpect(model().attributeExists("owner"));
        verify(ownerService, times(1)).save(any());

    }
    @Test
    void processFindFormEmptyReturnMany() throws Exception{
        when(ownerService.findAllByLastNameLike(anyString()))
                .thenReturn(
                        Arrays.asList(Owner.builder().id(1L).build(),
                                Owner.builder().id(2L).build()));

        mockMvc.perform(get("/owners").param("lastName", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownersList"))
                .andExpect(model().attribute("selections", hasSize(2)));


    }
}






















