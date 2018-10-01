package entity;

public class Person {
    private String name;
    private String address;
    private String  cash;

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String  getCash() {
        return cash;
    }

    public void setCash(String  cash) {
        this.cash = cash;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", cash='" + cash + '\'' +
                '}';
    }
}