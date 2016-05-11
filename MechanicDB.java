import java.util.ArrayList;

/**
 * Created by Patryk on 2016-05-08.
 */
public class MechanicDB {
    private ArrayList<Mechanic> database;

    public MechanicDB() {
        database = new ArrayList<Mechanic>();
    }

    public void addMechanic(Mechanic mechanic) {
        database.add(mechanic);
    }

    public void deleteMechanic(Mechanic mechanic) {
        database.remove(mechanic);
    }

    public ArrayList<Mechanic> getDatabase() {
        return database;
    }

}
