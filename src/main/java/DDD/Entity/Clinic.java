package DDD.Entity;

import DDD.Repository.TypeOfVisit;
import DDD.Servise.SymptomsDisease;
import DDD.Servise.LoggerInHistoryDisease;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Clinic {
    Deque<PersonPet> petRegistries = new ArrayDeque<>(10);
    List<PersonPet> petSurgeons = new ArrayList<>();
    List<PersonPet> petTherapists = new ArrayList<>();
    Deque<PersonPet> personPetTemporary = new ArrayDeque<>();

    private final Map<Person, HashSet<Pet>> customerPetMap = new HashMap<Person, HashSet<Pet>>();
    public LoggerInHistoryDisease logger = new LoggerInHistoryDisease();
    private final ReentrantLock lock = new ReentrantLock();
    private final ReentrantLock lock1 = new ReentrantLock();
    private final ReentrantLock lock2 = new ReentrantLock();
    private final Condition customerRegistry = lock2.newCondition();
    private final Condition customerTherapist = lock.newCondition();
    private final Condition customerSurgeon = lock.newCondition();

    public void customer(PersonPet personPet) {
        lock.lock();
        personPet.getPet().setTreatmentDecision(getTreatmentDecision(personPet.getPet().getSymptomeDisease()));
        if (TypeOfVisit.URGENT_VISIT != personPet.getPet().getTypeOfVisit()) {//если срочный визит
            personPetTemporary.add(personPet);
        } else {
            personPetTemporary.addFirst(personPet);

        }
        try {
            System.out.println(Thread.currentThread().getName() +
                    " пришел посетитель, их всего " + personPetTemporary.size());
        } finally {
            lock.unlock();
        }
        lock2.lock();
        if (personPetTemporary.size() > 0) {
            while (!lock2.tryLock()){
            customerRegistry.signal();}
        }
        try {
            System.out.println("клиент " + Thread.currentThread().getName() + " сигнал в регистратуру отправил, ждет регистрации");
            customerRegistry.await();
            System.out.println(Thread.currentThread().getName() + " клиент получил направление и может дальше идти по врачам");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock2.unlock();
            System.out.println(Thread.currentThread().getName() + " предыдущий поток разблокирован, новый клиент может пройти в регистратуру");
        }
        lock.lock();

        System.out.println(Thread.currentThread().getName() + " получил направление ко врачу "
                + personPet.getPet().getTreatmentDecision());
        try {
//            if (personPet.getPet().getTreatmentDecision().equals("направление к терапевту")) {
//                petTherapists.add(petRegistries.poll());//передача пациента терапевту
//                System.out.println("количестов посетителей к терапевту после " +
//                        "передачи клиента терапевту" + petTherapists.size());
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                customerTherapist.signal();
//                try {
//                    customerTherapist.await();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(Thread.currentThread().getName() + " пациент закончил прием у терапевта");
//            } else
            if (personPet.getPet().getTreatmentDecision().equals("направление к хирургу")) {
                System.out.println(Thread.currentThread().getName() + " условие  Если к хирургу сработало?");
                System.out.println(Thread.currentThread().getName() + " petRegistries.getFirst()= " + petRegistries.size());
                petSurgeons.add(petRegistries.getFirst());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " В регистратуре " + petRegistries.size() + "  приеме у хирурга в очереди " +
                        petSurgeons.size());
//               personPetTemporary.remove(0);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " количестов посетителей к хирургу" + petSurgeons.size() +
                        " регистратуре - " + petRegistries.size());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                customerSurgeon.signal();
                System.out.println(Thread.currentThread().getName() + " отдан сигнал хирургу, что он может начинать прием пациента");
                System.out.println("и поток клиента блокируется на время приема у хирурга");
                try {
                    customerSurgeon.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + " пациент закончил прием у хирурга");
        }

    }

    public void appointmentCustomer() {//запись на прием
        lock2.lock();
        while (personPetTemporary.size() > 0) {
            System.out.println(Thread.currentThread().getName() + " поток регистратора заблокирован. Размер желающих полечиться " + personPetTemporary.size());
            try {
                if (personPetTemporary.size() == 0) {
                    System.out.println(Thread.currentThread().getName() + " регистратор ждет посетителей");
                    customerRegistry.await();
                    System.out.println(Thread.currentThread().getName() + " регистратор должен был ждать посетителей но заработал");
                }
                petRegistries.add(personPetTemporary.poll());
                System.out.println("Перед формированием файла petRegistries размер = " + petRegistries.size() + " + " + personPetTemporary.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                while (!lock2.tryLock()) {
                    customerRegistry.signal();
                }
                lock2.unlock();

                File fileName = new File(petRegistries.element().getPet().getKindOfAnimal()
                        + "_" + petRegistries.element().getPet().getNickname() + ".txt");//создается название медицинской книги
                try {
                    if (fileName.createNewFile()) {
                        System.out.println(Thread.currentThread().getName() + " должен был создаться файл с именем " + petRegistries.getFirst().getPet().getHistoryDisease());
                        System.out.println(Thread.currentThread().getName() + " книга учета истории болезни создана");
                    }
                } catch (IOException ex) {
                    System.out.println(Thread.currentThread().getName() + " Книга учета истории болезни животного уже имеется в архиве");
                }
                logger.log("история болезни " + petRegistries.element().getPet().getKindOfAnimal()
                        + " " + petRegistries.element().getPet().getNickname(), fileName);
                logger.log("жалобы: " + petRegistries.element().getPet().getSymptomeDisease().getTitle(), fileName);
                String msg = petRegistries.element().getPet().getTreatmentDecision();
                logger.log(msg, fileName); //логируется результат направления к нужному специалисту
                System.out.println(Thread.currentThread().getName() + " после приема очередь зарегистрированных равна " + petRegistries.size());
                //дается сигнал посетителю,  получил направление и что ему нужно следовать дальше
                System.out.println(Thread.currentThread().getName() + " Следующий посетитель может пройти в регистратуру");

                //регистратор снова свободен и готов принимать новых пациентов
                System.out.println(Thread.currentThread().getName() + " закончил регистрацию" +
                        " и готов принять посетиля. поток регистратора разблокировался");
            }
        }
        System.out.println("Регистратор закончил работу, клиенты закончились");

    }

    public void treatmentByTherapist() {
        if (petTherapists.size() == 0) {
            try {
                customerTherapist.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        new EmployeeTherapist().examines(petTherapists.get(0).getPet().getHistoryDisease());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new EmployeeTherapist().prescribesTreatment(petTherapists.get(0).getPet().getHistoryDisease());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new EmployeeTherapist().vacinates(petTherapists.get(0).getPet().getHistoryDisease());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new EmployeeTherapist().registersTheResultOfTrearment(petTherapists.get(0).getPet().getHistoryDisease());
        logger.log("терапевт закончил лечение", petTherapists.get(0).getPet().getHistoryDisease());
        petTherapists.remove(0);
        customerTherapist.signal();
    }

    public void treatmentBySurgeon() {
        lock.lock();
        while (petRegistries.size() > 0 || petSurgeons.size() > 0) {
            if (petSurgeons.size() == 0) {
                System.out.println(Thread.currentThread().getName() + " хирург ждет пациентов");
                try {
                    customerSurgeon.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + " Табличка снаружи: идет прием у хирурга");
            System.out.println(Thread.currentThread().getName() + " имя файла, куда должна писаться книга болезни " + petSurgeons.get(0).getPet().getHistoryDisease());
            new EmployeeSurgeon().examines(petSurgeons.get(0).getPet().getHistoryDisease());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new EmployeeSurgeon().doEsnesthesia(petSurgeons.get(0).getPet().getHistoryDisease());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new EmployeeSurgeon().operates(petSurgeons.get(0).getPet().getHistoryDisease());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new EmployeeSurgeon().examines(petSurgeons.get(0).getPet().getHistoryDisease());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new EmployeeSurgeon().registersTheResultOfTrearment(petSurgeons.get(0).getPet().getHistoryDisease());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.log("хирург закончил лечение", petSurgeons.get(0).getPet().getHistoryDisease());
            System.out.println(Thread.currentThread().getName() + " клиент получил распечатку истории болезни питомца:\n");
            new HistoryDisease(petSurgeons.get(0).getPet().getHistoryDisease()).toPrintHistoryDisease();
            try {
                petSurgeons.remove(0);
            } finally {
                customerSurgeon.signal();
                lock.unlock();

            }
        }


        //    public void addDataBaseAnimal(PersonPet personPet) {//добавление животного в базу клиентов
//
//        if (!customerPetMap.containsKey(personPet.getPerson())) {//если такого клиента нет, то
//            customerPetMap.put(personPet.getPerson(), null);//создается в базе
//            customerPetMap.put(personPet.getPerson(), сгget(personPet.getPerson()).getPets().add(personPet.getPet());// питомец сохраняется в базе
//        }else {
//        if (!customerPetMap.get(personPet.getPerson()).getPets().contains(personPet.getPet())) {
//            //если питомца нет в базе
//            customerPetMap.get(personPet.getPerson()).getPets().add(personPet.getPet());// питомец сохраняется в базе
//            System.out.println("питомец добавлен в базу");
//        }}
//        System.out.println("такой питомец уже есть в базе");
//    }

    }

    public String getTreatmentDecision(SymptomsDisease symptomsPet) {
        if (symptomsPet == SymptomsDisease.REFUSAL_OF_FOOD_AND_WATER ||
                symptomsPet == SymptomsDisease.HOARSE_BREATHING ||
                symptomsPet == SymptomsDisease.ELEVATED_TEMPERATURE) {
            return "направление  терапевту";
        } else
            return "направление к хирургу";
    }
}
