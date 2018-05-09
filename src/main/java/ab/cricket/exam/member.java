package ab.cricket.exam;

public class member {
    private String firstName;
    private String lastName;
    private int CPR;
    private String adress;
    private int zipCode;
    private String city;

    //Constructors
    public member(String firstName, String lastName, int CPR, String adress, int zipCode, String city){
        this.firstName = firstName;
        this.lastName = lastName;
        this.CPR = CPR;
        this.adress = adress;
        this.zipCode = zipCode;
        this.city = city;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getCPR() {
        return CPR;
    }

    public void setCPR(int CPR) {
        this.CPR = CPR;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
