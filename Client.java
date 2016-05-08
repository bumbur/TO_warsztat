/**
 * Created by Patryk on 2016-05-08.
 */
public class Client extends UserAbstract {
    private String email;
    private String phone;

    public Client(String email, String name, String surname, String phone) {
        this.email = email;
        this.phone = phone;
        this.name=name;
        this.surname=surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}
