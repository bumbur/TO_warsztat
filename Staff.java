import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Patryk on 2016-05-08.
 */
public class Staff extends UserAbstract{
    private OrderDB orderDatabase;
    private MechanicDB mechanicDatabase;
    private HashMap<Order,Mechanic> assignedOrdersToMechanics = new HashMap<>();

    public Staff(String name, String surname, OrderDB orderDatabase, MechanicDB mechanicDatabase) {
        this.name=name;
        this.surname=surname;
        this.orderDatabase=orderDatabase;
        this.mechanicDatabase=mechanicDatabase;
    }

    public HashMap<Order, Mechanic> getAssignedOrdersToMechanics() {
        return assignedOrdersToMechanics;
    }

    public ArrayList<Mechanic> getMechanics(){
        return mechanicDatabase.getDatabase();
    }

    public ArrayList<Order> getOrders(){
        return orderDatabase.getDatabase();
    }

    public void setMechanicToOrder(Order order,Mechanic mechanic){
        order.setState(OrderState.IN_PROGRESS);
        assignedOrdersToMechanics.put(order,mechanic);
    }
}
