import java.util.ArrayList;

/**
 * Created by Patryk on 2016-05-09.
 */
public class ClientDB {
    private ArrayList<Client> database;

    public ClientDB(){
        this.database=new ArrayList<Client>();
    }

    public void addClient(Client client){
        database.add(client);
    }

    public void deleteClient(Client client){
        database.remove(client);
    }

    public ArrayList<Client> getDatabase(){
        return database;
    }
}
