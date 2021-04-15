import DDD.Entity.Clinic;
import Clinic.Employees.Registrar;
import DDD.Repository.SpecialityEmployee;
import DDD.Repository.TypeOfVisit;
import DDD.Servise.SymptomsDisease;
import DDD.Entity.Person;
import DDD.Entity.PersonPet;
import DDD.Entity.Pet;


public class Main {
    public static void main(String[] args) {
        Clinic clinic = new Clinic();
        Registrar registrar = new Registrar("Зоя", "Федоровна", SpecialityEmployee.REGISTRAR);
        PersonPet personPet = new PersonPet(new Person(
                "urij", "12324",
                "Юрий", "Юрьев", 1234567890),
                new Pet("собака", "Барбоскин",
                        3.5, "черный", null,
                        null, TypeOfVisit.BY_APPOINTMENT, SymptomsDisease.INJURY, null));
       PersonPet personPet1 = new PersonPet(new Person(
                "vasja", "12324",
                "Василий", "Васильев", 1234567890),
                new Pet("кошка", "Фурия",
                        2.3, "рыжий", null, null,
                        TypeOfVisit.URGENT_VISIT, SymptomsDisease.INJURY, null));

        new Thread(() -> {
            clinic.customer(personPet);
        }).start();

        new Thread(() -> {
            clinic.customer(personPet1);
        }).start();
        new Thread(null, clinic::treatmentBySurgeon, "хирург Плейшнер").start();
//        new Thread(null, clinic::treatmentByTherapist, "терапевт Таблеткин").start();
        new Thread(null, clinic::appointmentCustomer, "регистратор Баба Маня").start();
    }
//        Map<String, Customer> customerMap = new HashMap<>();
//        Customer customer = null;
//        AutorisationCustomer autorisationCustomer = new AutorisationCustomer();
//        RegistrationCustomer registrationCustomer = new RegistrationCustomer();
//        Appointment appointment = new Appointment();
//        Scanner scan = new Scanner(System.in);
//        String nickname;
//        String password;
//        boolean bool = true;
//        while (bool) {
//            System.out.println("Вы хотите авторизоваться (нажмите 1) " +
//                    ", заново зарегистироваться (нажмите 2) или любое другое " +
//                    "значение для выхода из системы");
//            String value = scan.nextLine();
//            if (value.equals("1")) {
//                nickname = getNickname(scan);
//                password = getPassword(scan);
//                int count = 0;
//                while (count < 3) {
//                    if (autorisationCustomer.autorisationCustomer(nickname, password,
//                            customerMap)) {
//                        customer = customerMap.get(nickname);//если авторизовался, то
////                        customer сваивается соответствующий клиент из базы
//                        bool = false;
//                    }
//                    count++;
//                }
//                System.out.println("Зарегистрируйтесь в системе");
//            }
//            if (value.equals("2")) {
//                String[] array = getDataCustom();
//                customer = registrationCustomer.getNewCustomer(customerMap, array);
//                bool = false;
//            } else break;
//        }
    /* Далее реализована медицинская книга. Принцип ее таков : на каждое животное создается файл
     *(показано на примере "HistoryDisease.txt") и логируются все действия медперсонала,
     *  начиная с момента первичного осмотра. Файл дозаписываемый и сохраняется на диске,
     *  и можно посмотреть его содержимое */


//    public static String getNickname(Scanner scan) {
//        System.out.println("Введите свой никнэйм");
//        return scan.nextLine();
//    }
//
//    public static String getPassword(Scanner scan) {
//        System.out.println("Введите пароль");
//        return scan.nextLine();
//    }
//
//    public static String[] getDataCustom() {
//        Scanner scan = new Scanner(System.in);
//        System.out.println("Введите свой никнэйм");
//        String nickname = scan.nextLine();
//        System.out.println("Введите пароль для авторизации");
//        String password = scan.nextLine();
//        System.out.println("Ваше имя");
//        String name = scan.nextLine();
//        System.out.println("Ваша фамилия");
//        String surname = scan.nextLine();
//        System.out.println("Введите номер расчетного счета");
//        String bankAccount = scan.nextLine();
//        return new String[] {nickname, password, name, surname, bankAccount};
//    }
}
