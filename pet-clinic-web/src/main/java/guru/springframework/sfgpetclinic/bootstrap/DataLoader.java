package guru.springframework.sfgpetclinic.bootstrap;


import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        dog.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Arnoldas");
        owner1.setLastName("Grevas");
        owner1.setAddress("123 Brickerel");
        owner1.setCity("Kaunas");
        owner1.setTelephone("861567893");

        Pet arnoldasPet = new Pet();
        arnoldasPet.setPetType(savedDogPetType);
        arnoldasPet.setOwner(owner1);
        arnoldasPet.setBirtDate(LocalDate.now());
        arnoldasPet.setName("Perlutė");
        owner1.getPets().add(arnoldasPet);
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Rytis");
        owner2.setLastName("Cicinas");
        owner2.setAddress("Kreves 19");
        owner2.setCity("Vilnius");
        owner2.setTelephone("864567893");

        Pet rytisCat = new Pet();
        rytisCat.setName("Plaukius");
        rytisCat.setOwner(owner2);
        rytisCat.setBirtDate(LocalDate.now());
        rytisCat.setPetType(savedCatPetType);
        owner2.getPets().add(rytisCat);

        ownerService.save(owner2);

        System.out.println("Loaded Owners...");
        Vet vet1 = new Vet();
        vet1.setFirstName("Algebrijus");
        vet1.setLastName("Runkelis");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Rimantas");
        vet2.setLastName("Bernadietis");

        vetService.save(vet2);

        System.out.println("Loaded Vets...");


    }
}

