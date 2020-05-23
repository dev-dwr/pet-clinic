package com.dwr.petclinic.controllers;

import com.dwr.petclinic.model.Owner;
import com.dwr.petclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/owners")
@Controller
public class OwnerController {
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService; //injecting ownerService
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder){
        //we are saying that we dont allow the web forms to address and manipulate the ID property
        dataBinder.setDisallowedFields("id");
    }


    @GetMapping("/find")
    public String findOwners(Model model){
        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";
    }

    @GetMapping
    public String processFindForm(Owner owner, BindingResult result, Model model){
        if(owner.getLastName() == null){
            owner.setLastName("");
        }

        List<Owner> resultOwners = ownerService.findAllByLastNameLike(owner.getLastName().toLowerCase());

        if(resultOwners.isEmpty()){
            result.rejectValue("lastName", "notFound", "not found");
            return "owners/findOwners";
        }else if(resultOwners.size() == 1){
            owner = resultOwners.get(0);
            return "redirect:/owners/" + owner.getId();
        }else{
            model.addAttribute("selections", resultOwners);
            return "owners/ownersList";
        }
    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwners(@PathVariable Long ownerId){
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject(ownerService.findById(ownerId));
        return mav;
    }
}
