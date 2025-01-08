package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeServices;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner{
    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeServices petTypeServices;

    @Autowired
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeServices petTypeServices) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeServices = petTypeServices;
    }

    @Override
    public void run(String... args) throws Exception {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogType = petTypeServices.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatType = petTypeServices.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Tadeo");
        owner1.setLastName("Guerstein");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Guru");
        owner2.setLastName("SpringFramework");

        ownerService.save(owner2);

        System.out.println("--------- Loaded owners");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Same");
        vet2.setLastName("Axe");

        vetService.save(vet2);

        System.out.println("--------- Loaded vets");


    }
}
