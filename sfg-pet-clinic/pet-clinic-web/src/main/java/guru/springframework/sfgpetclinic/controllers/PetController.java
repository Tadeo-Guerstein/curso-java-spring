package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @ModelAttribute("owner")
    public void initOwnerBinder(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }
}
