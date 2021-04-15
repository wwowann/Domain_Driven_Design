package DDD.Entity;

public class Person {
    private String nickname;
    private String password;
    private String name;
    private String surname;
    private long bancAccount;

    public Person(String nickname, String password, String name, String surname, long bancAccount) {
        this.nickname = nickname;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.bancAccount = bancAccount;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public long getBancAccount() {
        return bancAccount;
    }

    public void setBancAccount(long bancAccount) {
        this.bancAccount = bancAccount;
    }
}
