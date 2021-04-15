package DDD.Repository;

public enum SpecialityEmployee {
    THERAPIST ("терапевт"),
    SURGEON("хирург"),
    REGISTRAR("регистратор");
private final String title;

    SpecialityEmployee(String title) {
        this.title = title;
    }
    public String getTitle(){
        return title;
    }
}
