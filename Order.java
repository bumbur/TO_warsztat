import java.util.Date;
import java.util.HashMap;

/**
 * Created by Patryk on 2016-05-08.
 */
public class Order {
    private static int counter = 0;
    private int id;
    private Date dateStart;
    private OrderState state;
    private String mark;
    private String model;
    private String yearOfCar;
    private String mileage;
    private String description;
    private String repairDetails;
    private Client customer;


    private int cost;
    private HashMap<String, Integer> repairs;

    public Order(String mark, String model, String yearOfCar, String mileage, String description, Client customer) {
        this.id = counter++;
        this.dateStart = new Date();
        this.state = OrderState.ACCEPTED;
        this.mark = mark;
        this.model = model;
        this.yearOfCar = yearOfCar;
        this.mileage = mileage;
        this.description = description;
        this.customer = customer;
    }

    public HashMap<String, Integer> getRepairs() {
        return repairs;
    }

    public void setRepairs(HashMap<String, Integer> repairs) {
        this.repairs = repairs;
    }

    public int getId() {
        return id;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public String getStatus() {
        return state.toString();
    }

    public String getMark() {
        return mark;
    }

    public Client getCustomer() {
        return customer;
    }

    public String getModel() {
        return model;
    }

    public String getYearOfCar() {
        return yearOfCar;
    }

    public String getMileage() {
        return mileage;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }
}
