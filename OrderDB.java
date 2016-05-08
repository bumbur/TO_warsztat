import java.util.ArrayList;

/**
 * Created by Patryk on 2016-05-08.
 */
public class OrderDB {
    private ArrayList<Order> database;

    public OrderDB() {
        database= new ArrayList();
    }

    public void addOrder(Order order) {
        database.add(order);
    }

    public ArrayList<Order> getDatabase() {
        return database;
    }
}
