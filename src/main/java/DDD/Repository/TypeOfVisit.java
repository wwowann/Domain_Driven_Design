package DDD.Repository;

public enum TypeOfVisit {
    URGENT_VISIT ("срочный визит"),
    BY_APPOINTMENT ("по записи");
    private final String title;

    TypeOfVisit(String title) {
        this.title = title;
    }
    public String getTitle(){
        return title;
    }
}
