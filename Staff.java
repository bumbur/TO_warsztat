/**
 * Created by Patryk on 2016-05-08.
 */
public class Staff extends UserAbstract{
    OrderDB orderDatabase;

    public Staff(String name,String surname,OrderDB orderDatabase) {
        this.name=name;
        this.surname=surname;
        this.orderDatabase=orderDatabase;
    }
}
