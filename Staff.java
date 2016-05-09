import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Patryk on 2016-05-08.
 */
public class Staff extends UserAbstract{
    private OrderDB orderDatabase;
    private MechanicDB mechanicDatabase;
    private ClientDB clientsDatabase;
    private HashMap<Order,Mechanic> assignedOrdersToMechanics;

    public Staff(String name, String surname, OrderDB orderDatabase, MechanicDB mechanicDatabase,
                 ClientDB clientsDatabase, HashMap<Order, Mechanic> assignedOrdersToMechanics) {
        this.name=name;
        this.surname=surname;
        this.orderDatabase=orderDatabase;
        this.mechanicDatabase=mechanicDatabase;
        this.clientsDatabase = clientsDatabase;
        this.assignedOrdersToMechanics = assignedOrdersToMechanics;
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
