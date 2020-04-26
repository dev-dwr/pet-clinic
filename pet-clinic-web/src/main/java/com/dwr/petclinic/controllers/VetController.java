package com.dwr.petclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VetController {

    @RequestMapping({"/vets","/vets.html","vets/vets", "/vets/vets.html"})
    public  String listVets(){

        return "vets/vets.html"; //vets.html is in vets file
    }

}
