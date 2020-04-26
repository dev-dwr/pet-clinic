package com.dwr.petclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/owners")
@Controller
public class OwnerController {

    @RequestMapping({"", "/", "/owners", "/owners.html"})
    public String listOwners(){
        return "owners/owners.html";
    }
}
