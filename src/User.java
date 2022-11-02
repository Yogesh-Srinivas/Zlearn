import java.util.ArrayList;
import java.util.UUID;

enum role{
    LEARNER_ONLY,
    CREATOR_ONLY,
    LEARNER_AND_CREATOR;
}
public class User {
    private static int userIdGenerator = 1000;
    private int userId;
    private String userName;
    private String password;
    private String mobileNumber;

    public User(String userName,String password,String mobileNumber){
        this.userName = userName;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.userId = ++User.userIdGenerator;
    }

    public String getUserName() {
        return userName;
    }
    public String getPassword() {
        return password;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public int getUserId() {
        return userId;
    }
}
class Customer extends User{
    private String role;

    public Customer(String userName, String password, String mobileNumber) {
        super(userName, password, mobileNumber);
    }
    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}

class Admin extends User{
    public Admin(String userName, String password, String mobileNumber) {
        super(userName, password, mobileNumber);
    }

    void addCustomer(){

    }
    void editCustomet(){

    }
    void addCourses(){

    }
    void editCourses(){

    }

}