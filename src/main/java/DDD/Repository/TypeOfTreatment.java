package DDD.Repository;

public enum TypeOfTreatment {
    AMBULATORY_TREATMENT("амбулаторное лечение"),
    SURGERY("хирургическая операция");
    private final String title;

    TypeOfTreatment(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
