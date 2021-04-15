package DDD.Entity;

import DDD.Repository.ClinicEmployee;
import DDD.Servise.LoggerInHistoryDisease;

import java.io.File;

public class EmployeeSurgeon implements ClinicEmployee {
    public LoggerInHistoryDisease log = new LoggerInHistoryDisease();

    @Override
    public void examines(File file) {
        log.log(Thread.currentThread().getName() + " проводит осмотр", file);
    }

    @Override
    public void registersTheResultOfTrearment(File file) {

        log.log(Thread.currentThread().getName() + " регистрирует результат лечения", file);
    }

    public void operates(File file) {
        log.log(Thread.currentThread().getName() + " оперирует", file);
    }

    public void doEsnesthesia(File historyDisease) {

        log.log(Thread.currentThread().getName() + " делает анестезию", historyDisease);
    }
}
