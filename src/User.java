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
    void viewAllCourses(){

    }
    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
class Learner extends Customer{
    private ArrayList<String> myCourses = new ArrayList<>();

    public Learner(String userName, String password, String mobileNumber) {
        super(userName, password, mobileNumber);
        Database db = Database.getInstance();
        if(db.isLearnerACreator(mobileNumber)){
            super.setRole(role.LEARNER_AND_CREATOR.toString());
            db.changeCreatorRole(role.LEARNER_AND_CREATOR.toString(),mobileNumber);
        }
        else super.setRole(role.LEARNER_ONLY.toString());

    }

    void enrollCourse(){

    }
    void unenrollCourse(String courseId){
        this.myCourses.remove(courseId);
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

    public ArrayList<String> getMyCourses() {
        return myCourses;
    }
    public void setMyCourses(String courseId) {
        this.myCourses.add(courseId);
    }
}

class Creator extends Customer{
    public Creator(String userName, String password, String mobileNumber) {
        super(userName, password, mobileNumber);
        Database db = Database.getInstance();
        if(db.isCreatorALearner(mobileNumber)){
            super.setRole(role.LEARNER_AND_CREATOR.toString());
            db.changeLearnerRole(role.LEARNER_AND_CREATOR.toString(),mobileNumber);
        }
        else super.setRole(role.CREATOR_ONLY.toString());
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