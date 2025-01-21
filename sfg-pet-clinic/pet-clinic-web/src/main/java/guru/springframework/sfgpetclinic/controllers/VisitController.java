package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;

@Controller
public class VisitController {
    private final VisitService visitService;
    private final PetService petService;

    public VisitController(VisitService visitService, PetService petService) {
        this.visitService = visitService;
        this.petService = petService;
    }

    @InitBinder
    public void dataBinder(WebDataBinder webDataBinder){
        webDataBinder.setDisallowedFields("id");


        webDataBinder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport(){
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(LocalDate.parse(text));
            }
        });
    }

    /**
     * Llamado antes de cada @RequestMapping
     * Siempre permite tener data
     * Asegura que objeto Pet siempre tenga un id
    */
    @ModelAttribute("visit")
    public Visit loadPetWithVisit(@PathVariable Long petId, Model model){
        Pet pet = petService.findById(petId);

        model.addAttribute("pet", pet);
        Visit visit = new Visit();

        pet.getVisits().add(visit);
        visit.setPet(pet);

        return visit;
    }

    // loadPetWithVisit se va a ejecutar antes de que initNewVisitForm
    @GetMapping("/owners/*/pets/{petId}/visits/new")
    public String initNewVisitForm(@PathVariable Long petId, Model model){
        return "pets/createOrUpdateVisitForm";
    }

    @PostMapping("/owners/{ownerId}/pets/{petId}/visits/new")
    public String processNewVisitForm(@Validated Visit visit, BindingResult result){
        if(result.hasErrors()){
            return "pets/createOrUpdateVisitForm";
        }

        visitService.save(visit);
        return "redirect:/owners/{ownerId}";
    }
}
