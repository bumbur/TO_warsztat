import java.util.Date;

/**
 * Created by Patryk on 2016-05-08.
 */
public class Order {
    private int id;
    private Date dateStart;
    private Date dateEnd;
    private OrderState state;

    public Order(int id, Date dateStart, Date dateEnd, OrderState state) {
        this.id = id;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public OrderState getState() {
        return state;
    }
}
