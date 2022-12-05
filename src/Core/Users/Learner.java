package Core.Users;
import Managers.LearnerManager;
import Managers.UserManager;

import java.util.ArrayList;
import java.util.HashMap;

public class Learner extends User{
    private final HashMap<String,Double> enrolledCourses = new HashMap<>();
    private final LearnerManager learnerManager = new UserManager() ;

    private final ROLE role = ROLE.LEARNER;
    //******* Constructor ***************************************************************************

    public Learner(String userId,String userName,String password,String firstName){
        super(userId,userName,password,firstName);
    }

    //******* Getters and Setters ********************************************************************
    public ROLE getRole() {
        return role;
    }
    public ArrayList<String> getEnrolledCourses(){
        return new ArrayList<>(this.enrolledCourses.keySet());
    }

    public void addEnrolledCourse(String courseId){
        this.enrolledCourses.put(courseId,0.0);
    }

    public boolean isEnrolled(String courseId) {
        return this.enrolledCourses.containsKey(courseId);
    }

    //***************************************

    public double getCourseProgress(String courseId) {
        return this.enrolledCourses.get(courseId);
    }

    public void updateProgress(String courseId, double currentProgress){
        this.enrolledCourses.put(courseId,currentProgress);
    }

    //***************************************

    public void removeCourse(String courseId){
        this.enrolledCourses.remove(courseId);
    }

    //***************************************

    public void enrollNewCourse(String courseId){
        learnerManager.enrollNewCourse(courseId,this.getUserId());
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

    //***************************************
}
