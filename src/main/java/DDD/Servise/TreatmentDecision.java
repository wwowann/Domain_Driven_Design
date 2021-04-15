package DDD.Servise;

import Clinic.SymptomsDisease.SymptomsDisease;

public enum TreatmentDecision {
    TREATMENT_BY_A_THERAPIST("направление к терапевту"),
    TREATMENT_BY_A_SURGEON("направление к хирургу");
    private final String title;

   TreatmentDecision(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public TreatmentDecision getTreatmentDecision(SymptomsDisease symptomsPet) {
        if (symptomsPet == SymptomsDisease.REFUSAL_OF_FOOD_AND_WATER ||
                symptomsPet == SymptomsDisease.HOARSE_BREATHING ||
                symptomsPet == SymptomsDisease.ELEVATED_TEMPERATURE) {
            return TREATMENT_BY_A_THERAPIST;
        } else
            return TREATMENT_BY_A_SURGEON;
    }
}


