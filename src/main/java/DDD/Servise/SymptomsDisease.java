package DDD.Servise;

public enum SymptomsDisease {
    REFUSAL_OF_FOOD_AND_WATER ("отказ от корма и воды"),
    ELEVATED_TEMPERATURE ("повышенная темпереатура"),
    HOARSE_BREATHING ("хриплое дыхание"),
    INJURY ("травма");
     // и так далее
    private final String title;
    private SymptomsDisease(String title){
        this.title = title;
    }
    public String getTitle(){
        return title;
    }
}
