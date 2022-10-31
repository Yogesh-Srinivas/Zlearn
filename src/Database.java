import java.util.ArrayList;
import java.util.HashMap;


public class Database {
    private Database(){}
    private static Database instance = null;
    public static Database getInstance(){
        if(instance == null){
            instance= new Database();
        }
        return instance;
    }
    private HashMap<String,ArrayList<HashMap<Integer,Double>>> userProgress = new HashMap<>();
    private ArrayList<Course> courses = new ArrayList<>();
    private ArrayList<Learner> learners = new ArrayList<>();
    private ArrayList<Creator> creators = new ArrayList<>();
    private ArrayList<String> courseCategory = new ArrayList<>();

    public void addLearner(Learner learner){
        this.learners.add(learner);
    }
    public void addCreator(Creator creator){
        this.creators.add(creator);
    }

    public void addUserProgress(String courseId,int userId){
        this.userProgress.get(courseId).add(new HashMap<Integer,Double>(){{put(userId,0.0);}});
    }
    public void updateUserProgress(String courseId, int userId, double currProgressStepValue){
        double oldProgress = 0.0;
        for(HashMap<Integer,Double> individualUserProgress : this.userProgress.get(courseId)){
            if(individualUserProgress.containsKey(userId)){
                oldProgress = individualUserProgress.get(userId);
            }
        }
        double currentProgress = oldProgress+currProgressStepValue;
        if(currentProgress > 99.0) currentProgress=100.0;
        for(int i=0;i<this.userProgress.get(courseId).size();i++){
            if(this.userProgress.get(courseId).get(i).containsKey(userId)){
                this.userProgress.get(courseId).get(i).put(userId,currentProgress);
                break;
            }
        }
    }
    public double getUserCurrentProgress(String courseId, int userId){
        double currentProgress = 0 ;
        for(HashMap<Integer,Double> individualUserProgress : this.userProgress.get(courseId)){
            if(individualUserProgress.containsKey(userId)){
               currentProgress = individualUserProgress.get(userId);
               break;
            }
        }
        return currentProgress;
    }
    Course getCourseDetails(String courseId){
        for(Course c : this.courses){
            if(c.getCourseId().equals(courseId)){
                return c;
            }
        }

        return new Course();
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
    boolean isCreatorALearner(String mobile){
        for(Learner l : this.learners){
            if(l.getMobileNumber().equals(mobile)) return true;
        }
        return false;
    }
    boolean isLearnerACreator(String mobile){
        for(Creator c : this.creators){
            if(c.getMobileNumber().equals(mobile)) return true;
        }
        return false;
    }
    void changeLearnerRole(String role,String mobile){
        for(Learner l : this.learners){
            if(l.getMobileNumber().equals(mobile)){
                l.setRole(role);
            }
        }
    }
    void changeCreatorRole(String role,String mobile){
        for(Creator c : this.creators){
            if(c.getMobileNumber().equals(mobile)){
                c.setRole(role);
            }
        }
    }

    public String customerAuthentication(String mobile,String password){
        for(Learner l : this.learners){
            if(l.getMobileNumber().equals(mobile)){
                if(l.getPassword().equals(password)){
                    if(l.getRole().equals(role.LEARNER_ONLY.toString())){
                        return role.LEARNER_ONLY.toString();
                    }else{
                        return role.LEARNER_AND_CREATOR.toString();
                    }
                }
            }
        }
        for (Creator c : this.creators){
            if(c.getMobileNumber().equals(mobile)){
                if(c.getPassword().equals(password)){
                    return role.CREATOR_ONLY.toString();
                }
            }
        }
        return "User Not Found";
    }

    public void addCourses(Course course) {
        this.courses.add(course);
        this.userProgress.put(course.getCourseId(), new ArrayList<>());
    }
}


class Authenticator{

    public static String customerLogin(String mobile,String password){
        Database db = Database.getInstance();
        return db.customerAuthentication(mobile,password);
    }
    void logout(){

    }

}
