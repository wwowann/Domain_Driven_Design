package DDD.Servise;

import DDD.Entity.PersonPet;

public class QueuePetSurdeon {
      private final PersonPet personPet;
              public QueuePetSurdeon(PersonPet personPet) {
            this.personPet = personPet;

        }

    public PersonPet getPersonPet() {
        return personPet;
    }
}
