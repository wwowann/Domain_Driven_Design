package DDD.Servise;

import DDD.Entity.PersonPet;

public class QueuePetTherapist {
    private final PersonPet personPet;

    public QueuePetTherapist(PersonPet personPet) {
        this.personPet = personPet;

    }

    public PersonPet getPersonPet() {
        return personPet;
    }


}
