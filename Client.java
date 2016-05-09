/**
 * Created by Patryk on 2016-05-08.
 */
public class Client extends UserAbstract {
    private String email;
    private String phone;
    private OrderDB database;

    public Client(String email, String name, String surname, String phone,OrderDB database) {
        this.email = email;
        this.phone = phone;
        this.name=name;
        this.surname=surname;
        this.database=database;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public void addOrder(String mark, String model, String yearOfCar, String mileage, String description){
        Order order=new Order(mark, model, yearOfCar, mileage, description);
        database.addOrder(order);
    }

    public Order returnGivenOrder(int id){
        return database.getOrderByID(id);
    }
}
