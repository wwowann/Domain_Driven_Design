package DDD.Servise;

import DDD.Entity.PersonPet;

public class QueuePetRegistry {
    private final PersonPet personPet;

    public QueuePetRegistry(PersonPet personPet) {
        this.personPet = personPet;

    }

    public PersonPet getPersonPet() {
        return personPet;
    }


}
