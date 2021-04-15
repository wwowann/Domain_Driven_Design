package DDD.Servise;

import DDD.Entity.Customer;

import java.util.Map;


public class AutorisationCustomer {


    public boolean autorisationCustomer(String nickname, String password,
                                        Map<String, Customer> customer) {
        for (Map.Entry<String, Customer> person : customer.entrySet()) {
            if (!person.getKey().equals(nickname) &&
                    !person.getValue().getPerson().getPassword().equals(password)) {
                System.out.println("Такого пользователя не существует. Зарегистрируйтесь");
                return false;
            }
            System.out.println("Добро пожаловать " + nickname + " пароль: " + password);
        }
        return true;
    }
}
