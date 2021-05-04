package guru.springframework.sfgpetclinic.model;

public class Specialty extends BaseEntity {

    private String description;


    // Getters and Setters

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
