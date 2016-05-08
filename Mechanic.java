/**
 * Created by Patryk on 2016-05-08.
 */
public class Mechanic extends UserAbstract{
    private Boolean isWorking;

    public Mechanic(String name, String surname) {
        this.isWorking = false;
        this.name=name;
        this.surname=surname;
    }

    public Boolean getIsWorking(){
        return isWorking;
    }

    public void setIsWorking(Boolean state){
        isWorking = state;
    }
}
