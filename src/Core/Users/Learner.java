package Core.Users;
import Managers.LearnerManager;
import Managers.UserManager;

import java.util.ArrayList;
import java.util.HashMap;

public class Learner extends User{
    private HashMap<String,Double> enrolledCourses = new HashMap<>();
    private LearnerManager learnerManager = new UserManager() ;
    //***************************************************************************
    public Learner(String userId,String userName,String password,String firstName,ROLE role){
        super(userId,userName,password,firstName,role);
    }

    public void addEnrolledCourse(String courseId){
        this.enrolledCourses.put(courseId,0.0);
    }

    public void updateProgress(String courseId, double currentProgress){
        this.enrolledCourses.put(courseId,currentProgress);
    }
    public void removeCourse(String courseId){
        this.enrolledCourses.remove(courseId);
    }

    //***** Learner Operations *****************************************************
    public void enrollNewCourse(String courseId){
        learnerManager.enrollNewCourse(courseId,this.getUserId());
    }

    public ArrayList<String> getEnrolledCourses(){
        ArrayList<String> courses = new ArrayList<>();
        for(String courseId : this.enrolledCourses.keySet()){
            courses.add(courseId);
        }
        return courses;
    }

    public void updateCourseProgress(String courseId,double currentProgress){
        learnerManager.updateCourseProgress(courseId,currentProgress,this.getUserId());
    }

    public void addComment(String comment,String courseId){
        learnerManager.addComment(comment,courseId,this.getUserId());
    }

    public void rateCourse(String courseId,int rating){
        learnerManager.rateCourse(courseId,rating,this.getUserId());
    }

    public void unenrollCourse(String courseId){
        learnerManager.unenrollCourse(courseId,this.getUserId());
    }

    public double getCourseProgress(String courseId) {
        return this.enrolledCourses.get(courseId);
    }
}
