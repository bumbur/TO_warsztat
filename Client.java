/**
 * Created by Patryk on 2016-05-08.
 */
public class Client extends UserAbstract {
    private String email;
    private String phone;
    private OrderDB database;

    public Client(String name, String surname, String email, String phone, OrderDB database) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.database = database;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public int addOrder(String mark, String model, String yearOfCar, String mileage, String description, Client customer) {
        Order order = new Order(mark, model, yearOfCar, mileage, description, customer);
        database.addOrder(order);
        return order.getId();
    }

    public Order returnGivenOrder(int id) {
        return database.getOrderByID(id);
    }
}
