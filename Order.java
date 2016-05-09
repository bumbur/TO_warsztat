import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Patryk on 2016-05-08.
 */
public class Order {
    private static int counter=0;
    private int id;
    private Date dateStart;
    private OrderState state;
    private String mark;
    private String model;
    private String yearOfCar;
    private String mileage;
    private String description;
    private int cost;
    private HashMap<String,Integer> repairs;

    public Order(String mark, String model, String yearOfCar, String mileage, String description) {
        this.id = counter++;
        this.dateStart = new Date();
        this.state = OrderState.ACCEPTED;
        this.mark = mark;
        this.model = model;
        this.yearOfCar = yearOfCar;
        this.mileage = mileage;
        this.description = description;
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

    public String getMark() {
        return mark;
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

    public String getDescription() {
        return description;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public void setRepairs(HashMap<String,Integer> repairs){
        this.repairs=repairs;
    }

    public void updateCost(){
        for(Map.Entry<String,Integer> entry : repairs.entrySet()){
            this.cost+=entry.getValue();
        }
    }
}
