/**
 * Created by Patryk on 2016-05-08.
 */
public class Staff extends UserAbstract{
    OrderDB orderDatabase;

    public Staff(String name,String surname,OrderDB orderDatabase) {
        this.names=name;
        this.surname=surname;
        this.orderDatabase=orderDatabase;
    }
}
