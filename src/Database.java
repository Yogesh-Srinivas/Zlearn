import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;


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
    private ArrayList<String> courseCategories = new ArrayList<>();

    //Learner Methods
    public void addLearner(Learner learner){
        this.learners.add(learner);
    }
    public void addCreator(Creator creator){
        this.creators.add(creator);
    }
    public String getCreatorName(int userId){
        String creatorName = "null";
        for(Creator creator:this.creators){
            if(creator.getUserId() == userId){
                creatorName = creator.getUserName();
            }
        }
        return creatorName;
    }
    public String getLearnerName(int userId){
        String learnerName = "null";
        for(Learner learner:this.learners){
            if(learner.getUserId() == userId){
                learnerName = learner.getUserName();
            }
        }
        return learnerName;
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
    public void enrollCourse(String courseId,int userId){
        addUserProgress(courseId,userId);
    }
    public void unenrollCourse(String courseId,int userId){
        int userProgressIndex=0;
        for(HashMap<Integer,Double> individualUserProgress : this.userProgress.get(courseId)){
            if(individualUserProgress.containsKey(userId)){
                userProgressIndex = this.userProgress.get(courseId).indexOf(individualUserProgress);
                break;
            }
        }
        this.userProgress.get(courseId).remove(userProgressIndex);
    }
    public Course getCourseDetails(String courseId){
        for(Course c : this.courses){
            if(c.getCourseId().equals(courseId)){
                return c;
            }
        }

        return new Course();
    }
    public ArrayList<String> getCategories(){
        ArrayList<String> categories = new ArrayList<>();
        for(String category:this.courseCategories) categories.add(category);
        return categories;
    }
    public void addCategory(String newCategory){
        this.courseCategories.add(newCategory);
    }
    public ArrayList<Course> getCoursesBasedOnCategory(String category){
        ArrayList<Course> filteredCourses = new ArrayList<>();
        for(Course course : this.courses){
            if(course.getCourseCategories().contains(category))
                filteredCourses.add(course);
        }
        return filteredCourses;
    }
    public boolean isCreatorALearner(String mobile){
        for(Learner l : this.learners){
            if(l.getMobileNumber().equals(mobile)) return true;
        }
        return false;
    }
    public boolean isLearnerACreator(String mobile){
        for(Creator c : this.creators){
            if(c.getMobileNumber().equals(mobile)) return true;
        }
        return false;
    }
    public void changeLearnerRole(String role,String mobile){
        for(Learner l : this.learners){
            if(l.getMobileNumber().equals(mobile)){
                l.setRole(role);
            }
        }
    }
    public void changeCreatorRole(String role,String mobile){
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
    public void addComment(String courseId,String comment,int commenter){
        int courseIndex=0;
        for(Course course : this.courses){
            if(course.getCourseId().equals(courseId)){
                courseIndex = this.courses.indexOf(course);
            }
        }
        this.courses.get(courseIndex).addComment(comment,commenter);
    }
    public void rateCourse(String courseId,int userId,int rating){
        int courseIndex=0;
        for(Course course : this.courses){
            if(course.getCourseId().equals(courseId)){
                courseIndex = this.courses.indexOf(course);
            }
        }
        this.courses.get(courseIndex).updateRating(userId,rating);
    }

    //Creator Methods
    public ArrayList<Course> getCreatorCourses(int userId){
        ArrayList<Course> creatorCourses = new ArrayList<>();
        for(Course course:this.courses){
            if(course.getCreatorId() == userId){
                creatorCourses.add(course);
            }
        }
        return creatorCourses;
    }
    public void changeCourseName(String courseId,String newCourseName){
        int courseIndex=0;
        for(Course course : this.courses){
            if(course.getCourseId().equals(courseId)){
                courseIndex = this.courses.indexOf(course);
            }
        }
        this.courses.get(courseIndex).changeCourseName(newCourseName);
    }
    public void addCategoryToCourse(String courseId,String categoryName){
        int courseIndex=0;
        for(Course course : this.courses){
            if(course.getCourseId().equals(courseId)){
                courseIndex = this.courses.indexOf(course);
            }
        }
        this.courses.get(courseIndex).addCategory(categoryName);
    }
    public void deleteCategoryFromCourse(String courseId,String categoryName){
        int courseIndex=0;
        for(Course course : this.courses){
            if(course.getCourseId().equals(courseId)){
                courseIndex = this.courses.indexOf(course);
            }
        }
        this.courses.get(courseIndex).deleteCategory(categoryName);
    }
    public void changeCoursePrice(String courseId,int newCourePrice){
        int courseIndex=0;
        for(Course course : this.courses){
            if(course.getCourseId().equals(courseId)){
                courseIndex = this.courses.indexOf(course);
            }
        }
        this.courses.get(courseIndex).changePrice(newCourePrice);
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
