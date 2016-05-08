import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Patryk on 2016-05-08.
 */
public class Staff extends UserAbstract{
    private OrderDB orderDatabase;
    private MechanicDB mechanicDatabase;
    private HashMap<Order,Mechanic> map;

    public Staff(String name,String surname,OrderDB orderDatabase,MechanicDB mechanicDatabase) {
        this.name=name;
        this.surname=surname;
        this.orderDatabase=orderDatabase;
        this.mechanicDatabase=mechanicDatabase;
        this.map=new HashMap<Order,Mechanic>();
    }

    public int setMechanicToOrder(Order order){
        /*returns 0 if success
        returns -1 if there is no available mechanics*/
        orderDatabase.addOrder(order);
        Mechanic mechanic=mechanicDatabase.getFreeMechanic();
        if(mechanic==null)return -1;
        mechanic.setIsWorking(true);
        map.put(order,mechanic);
        return 0;
    }

    public ArrayList<Order> getOrdersForMechanic(Mechanic mechanic){
        ArrayList<Order> orders=new ArrayList<Order>();

        for(Map.Entry<Order,Mechanic> entry : map.entrySet()){
            if(entry.getValue()==mechanic)orders.add(entry.getKey());
        }

        return orders;
    }

}
