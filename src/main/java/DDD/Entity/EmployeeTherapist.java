package DDD.Entity;

import DDD.Repository.ClinicEmployee;
import DDD.Servise.LoggerInHistoryDisease;

import java.io.File;

public class EmployeeTherapist implements ClinicEmployee {
    public LoggerInHistoryDisease log;

    @Override
    public void examines(File file) {
        System.out.println("терапевт" + Thread.currentThread().getName() + " проводит осмотр");
        log.log("терапевт" + Thread.currentThread().getName() + " проводит осмотр", file);
    }

    @Override
    public void registersTheResultOfTrearment(File file) {
        System.out.println("терапевт регистрирует результат лечения");
        log.log("терапевт" + Thread.currentThread().getName() + " регистрирует результат лечения", file);
    }

    public void vacinates(File file) {
        System.out.println("терапевт делает прививку");
        log.log("терапевт" + Thread.currentThread().getName() + " делает прививку", file);
    }

    public void prescribesTreatment(File file) {
        System.out.println("терапевт назначает лечение");
        log.log("терапевт" + Thread.currentThread().getName() + "назначает лечение", file);
    }
}
