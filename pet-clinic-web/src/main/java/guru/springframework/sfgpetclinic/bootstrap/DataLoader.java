package guru.springframework.sfgpetclinic.bootstrap;


import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
                      SpecialtyService specialtyService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();

        if (count == 0) {
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        dog.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology");
        Specialty savedRadiology = specialtyService.save(radiology);

        Specialty surgery = new Specialty();
        surgery.setDescription("Surgery");
        Specialty savedSurgery = specialtyService.save(surgery);

        Specialty dentistry = new Specialty();
        dentistry.setDescription("Dentistry");
        Specialty savedDentistry = specialtyService.save(dentistry);

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


        Visit catVisit = new Visit();
        catVisit.setPet(rytisCat);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Cat trying to commit a suicide");

        visitService.save(catVisit);


        System.out.println("Loaded Owners...");
        Vet vet1 = new Vet();
        vet1.setFirstName("Algebrijus");
        vet1.setLastName("Runkelis");
        vet1.getSpecialties().add(savedRadiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Rimantas");
        vet2.setLastName("Bernadietis");
        vet2.getSpecialties().add(savedSurgery);

        vetService.save(vet2);

        System.out.println("Loaded Vets...");
    }
}

