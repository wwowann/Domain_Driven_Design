package DDD.Entity;

import Clinic.Registry.TypeOfVisit;
import Clinic.SymptomsDisease.SymptomsDisease;

import java.io.File;

public class Pet {
    private String kindOfAnimal;
    private String nickname;
    private double montAndYearBirth;
    private String suit;
    private File historyDisease;
    private String statusTreatment;
    private final TypeOfVisit typeOfVisit;
    private final SymptomsDisease symptomeDisease;
    private String treatmentDecision;

    public Pet(String kindOfAnimal, String nickname, double montAndYearBirth,
               String suit, File historyDisease, String statusTreatment,
               TypeOfVisit typeOfVisit, SymptomsDisease symptomeDisease, String treatmentDecision) {
        this.kindOfAnimal = kindOfAnimal;
        this.nickname = nickname;
        this.montAndYearBirth = montAndYearBirth;
        this.suit = suit;
        this.historyDisease = returnFileName();
        this.statusTreatment = statusTreatment;
        this.typeOfVisit = typeOfVisit;
        this.symptomeDisease = symptomeDisease;
        this.treatmentDecision = treatmentDecision;
        }
    public String getTreatmentDecision() {
        return treatmentDecision;
    }

    public void setTreatmentDecision(String treatmentDecision) {
        this.treatmentDecision = treatmentDecision;
    }
   private File returnFileName() {
        return new File(kindOfAnimal + "_" + nickname + ".txt");
    }

    public SymptomsDisease getSymptomeDisease() {
        return symptomeDisease;
    }

    public TypeOfVisit getTypeOfVisit() {
        return typeOfVisit;
    }

    public void setRindOfAnimal(String rindOfAnimal) {
        this.kindOfAnimal = rindOfAnimal;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setAge(double montAndYearBirth) {
        this.montAndYearBirth = montAndYearBirth;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public void setHistoryDisease(File historyDisease) {
        this.historyDisease = historyDisease;
    }

    public String getKindOfAnimal() {
        return kindOfAnimal;
    }

    public String getNickname() {
        return nickname;
    }

    public double getMontAndYearBirth() {
        return montAndYearBirth;
    }

    public String getSuit() {
        return suit;
    }

    public File getHistoryDisease() {
        return historyDisease;
    }

    public String getStatusTreatment() {
        return statusTreatment;
    }

    public void setStatusTreatment(String statusTreatment) {
        this.statusTreatment = statusTreatment;
    }



}
