import java.util.ArrayList;

public class Database {
    private Database(){}
    private static Database instance = null;
    public static Database getInstance(){
        if(instance!=null){
            instance= new Database();
        }
        return instance;
    }

    private static ArrayList<Course> courses = new ArrayList<>();
    private static ArrayList<Learner> learners = new ArrayList<Learner>();
    private static ArrayList<Creator> creators = new ArrayList<>();
    private static ArrayList<String> courseCategory = new ArrayList<>();

    public static void addLearner(Learner learner){
        Database.learners.add(learner);
    }
    void getCourseDetails(){

    }
    void getLearnerDetails(){

    }
    void getCreatorDetails(){

    }
    void getCategory(){

    }
    void addCategory(){

    }
    void deleteCategory(){

    }
}


class Authenticator{

    public static boolean customerLogin(String mobile,String password){
        return true;
    }
    void logout(){

    }

}
