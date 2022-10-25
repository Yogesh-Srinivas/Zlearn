import java.util.ArrayList;
enum role{

}
public class User {
    private String userId;
    private String userName;
    private String password;
    private String mobileNumber;
    public User(String userName,String password,String mobileNumber){
        this.userName = userName;
        this.password = password;
        this.mobileNumber = mobileNumber;
    }
}
class Customer extends User{
    private String role;

    public Customer(String userName, String password, String mobileNumber) {
        super(userName, password, mobileNumber);
    }

    void viewAllCourses(){

    }
}
class Learner extends Customer{
    private ArrayList<String> myCourses = new ArrayList<>();

    public Learner(String userName, String password, String mobileNumber) {
        super(userName, password, mobileNumber);
    }

    void enrollCourse(){

    }
    void startLearning(){

    }
    void addComments(){

    }
    void editComments(){

    }
    void deleteComments(){

    }
    void showCertificates(){

    }
    void rateCourse(){

    }
    void viewEnrolledCourse(){

    }
}

class Creator extends Customer{
    public Creator(String userName, String password, String mobileNumber) {
        super(userName, password, mobileNumber);
    }
    void createCourse(){

    }
    void deleteCourse(){

    }
    void editCourse(){

    }
    void viewCreatedCourse(){

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