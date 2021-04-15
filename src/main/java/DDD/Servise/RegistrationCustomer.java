package DDD.Servise;

import DDD.Entity.Customer;
import DDD.Entity.Person;

import java.util.Map;

public class RegistrationCustomer {
    public Customer getNewCustomer(Map<String, Customer> customer, String[] array) {
       Customer person = null;
        boolean bool = true;
        while (bool) {
            String nickname = array[0];
            String password = array[1];
            String name = array[2];
            String surname = array[3];
            long bankAccount = Long.parseLong(array[4].trim());
            if (customer.containsKey(array[0])) {
                System.out.println("пользователь с таким никнэймом уже зарегистрирован в системе, " +
                        "выберите другой никнэйм");
                bool = true;
            } else {
                person = new Customer(new Person(nickname, password, name,
                        surname, bankAccount), null);
                customer.put(nickname, person);
                bool = false;
            }
        }
        return person;
    }
}
