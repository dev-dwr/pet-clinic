package com.dwr.petclinic.controllers;

import com.dwr.petclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/owners")
@Controller
public class OwnerController {
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService; //injecting ownerService
    }

    @GetMapping({"", "/", "/owners", "/owners.html"})
    public String listOwners(Model model){
        model.addAttribute("owners", ownerService.findAll());
        return "owners/owners.html"; //view
    }

    @GetMapping("/find")
    public String findOwners(){

        return "notimplemented.html";
    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwners(@PathVariable Long ownerId){
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject(ownerService.findById(ownerId));
        return mav;
    }
}
