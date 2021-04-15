package DDD.Entity;

import DDD.Pet;

public class PersonPet {
    private final Person person;
    private final Pet pet;

    public PersonPet(Person person, Pet pet) {
        this.person = person;
        this.pet = pet;
    }

    public Person getPerson() {
        return person;
    }

    public Pet getPet() {
        return pet;
    }
}
