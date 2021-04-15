package DDD.Servise;

import Customers.PersonPet;

public class QueuePetTherapist {
    private final PersonPet personPet;

    public QueuePetTherapist(PersonPet personPet) {
        this.personPet = personPet;

    }

    public PersonPet getPersonPet() {
        return personPet;
    }


}
