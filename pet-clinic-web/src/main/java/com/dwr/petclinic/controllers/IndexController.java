package com.dwr.petclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping({"", "/index", "index.html"}) //when the request comes in to  the root context or root slash index or vets.html the are gonna match to the this RequestMapping
    public String index(){

        return "index";
    }
}
