package DDD.Entity;

import DDD.Repository.SpecialityEmployee;

public class Registrar {
    public String name = null;
    public String surname = null;
    public SpecialityEmployee specialityEmployee = SpecialityEmployee.REGISTRAR;

    public Registrar(String name, String surname, SpecialityEmployee specialityEmployee) {
        this.name = name;
        this.surname = surname;
        this.specialityEmployee = specialityEmployee;
    }
}
