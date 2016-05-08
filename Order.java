import java.util.Date;

/**
 * Created by Patryk on 2016-05-08.
 */
public class Order {
    private static int counter=0;
    private int id;
    private Date dateStart;
    private Date dateEnd;
    private OrderState state;
    private String mark;
    private String model;
    private String yearOfCar;
    private String course;
    private String description;

    public Order(String mark, String model, String yearOfCar, String course, String description) {
        this.id = counter++;
        this.dateStart = new Date();
        this.state = OrderState.ACCEPTED;
        this.mark = mark;
        this.model = model;
        this.yearOfCar = yearOfCar;
        this.course = course;
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

    public String getCourse() {
        return course;
    }

    public String getDescription() {
        return description;
    }
}
