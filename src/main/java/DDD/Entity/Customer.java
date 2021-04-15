package DDD.Entity;

import DDD.Servise.SymptomsDisease;

import java.util.HashSet;
import java.util.Set;

public class Customer {
    private Person person;
    private HashSet<Pet> pets;

    public Customer(Person person, HashSet<Pet> pets) {
        this.person = person;
        this.pets = pets;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(HashSet<Pet> pets) {
        this.pets = pets;
    }

    public void newPetByCustomer(Pet pet, SymptomsDisease symptomsDisease){

    }


}
