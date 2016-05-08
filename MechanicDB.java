import java.util.ArrayList;

/**
 * Created by Patryk on 2016-05-08.
 */
public class MechanicDB {
    private ArrayList<Mechanic> database;

    public MechanicDB() {
        database= new ArrayList();
    }

    public void addMechanic(Mechanic mechanic) {
        database.add(mechanic);
    }

    public void deleteMechanic(Mechanic mechanic){
        database.remove(mechanic);
    }

    public Mechanic getFreeMechanic(){
        for(int i=0;i<database.size();i++){
            if(database.get(i).getIsWorking()==false)return database.get(i);
        }
        return null;
    }

    public ArrayList<Mechanic> getDatabase() {
        return database;
    }

}
