package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {
    private static final String VIEW_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";
    private final PetService petServices;
    private final OwnerService ownerService;
    private final PetTypeServices petTypeServices;

    public PetController(PetService petServices, OwnerService ownerService, PetTypeServices petTypeServices) {
        this.petServices = petServices;
        this.ownerService = ownerService;
        this.petTypeServices = petTypeServices;
    }

    @ModelAttribute("types")
    public Collection<PetType> populatePetTypes() {
        return petTypeServices.findAll();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable Long ownerId){
        return ownerService.findById(ownerId);
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/pets/new")
    public String initCreationForm(Owner owner, Model model){
        Pet pet = new Pet();
        owner.getPets().add(pet);
        pet.setOwner(owner);
        model.addAttribute("pet", pet);
        return VIEW_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/pets/new")
    public String processCreationForm(Owner owner, @Validated Pet pet, BindingResult result, Model model){
        if(StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(), true) != null){
            result.rejectValue("name", "duplicate", "already exists");
        }
        owner.getPets().add(pet);
        if(result.hasErrors()){
            model.addAttribute("pet", pet);
            return VIEW_PETS_CREATE_OR_UPDATE_FORM;
        }

        petServices.save(pet);
        return "redirect:/owners/" + owner.getId();
    }

    @GetMapping("pets/{petId}/edit")
    public String initUpdateForm(@PathVariable Long petId, Model model){
        model.addAttribute("pet", petServices.findById(petId));
        return VIEW_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("pets/{petId}/edit")
    public String processUpdateForm(@Validated Pet pet, BindingResult result, Owner owner, Model model){
        if(result.hasErrors()){
            pet.setOwner(owner);
            model.addAttribute("pet", pet);
            return VIEW_PETS_CREATE_OR_UPDATE_FORM;
        }

        owner.getPets().add(pet);
        petServices.save(pet);
        return "redirect:/owners/" + owner.getId();
    }
}
