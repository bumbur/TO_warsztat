import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Patryk on 2016-05-08.
 */
public class Mechanic extends UserAbstract {
    private HashMap<Order, Mechanic> assignedOrdersToMechanics;
    private HashMap<String, Integer> repairs;

    public Mechanic(String name, String surname,
                    HashMap<Order, Mechanic> assignedOrdersToMechanics) {//, HashMap<String,Integer> repairs) {
        this.name = name;
        this.surname = surname;
        this.assignedOrdersToMechanics = assignedOrdersToMechanics;
        //this.repairs = repairs;
    }

    public void setRepairsToOrder(Order order, HashMap<String, Integer> repairs) {
        order.setRepairs(repairs);
    }

    public void updateCostOfOrder(Order order) {
        order.updateCost();
    }

    public void endOrder(Order order) {
        order.setState(OrderState.DONE);
    }

    public ArrayList<Order> getOrdersForMechanic() {
        ArrayList<Order> orders = new ArrayList<Order>();

        for (Map.Entry<Order, Mechanic> entry : assignedOrdersToMechanics.entrySet()) {
            if (entry.getValue() == this) orders.add(entry.getKey());
        }
        return orders;
    }

    public HashMap<String, Integer> getRepairs() {
        return repairs;
    }
}
