package guru.springframework.sfgpetclinic.controllers;


import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import org.springframework.stereotype.Controller;

@Controller
public class VisitController {

    private final VisitRepository visitRepository;
    private final PetRepository petRepository;

    public VisitController(VisitRepository visitRepository, PetRepository petRepository) {
        this.visitRepository = visitRepository;
        this.petRepository = petRepository;
    }


}
