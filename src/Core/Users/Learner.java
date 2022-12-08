package Core.Users;
import Managers.LearnerManager;
import Managers.UserManager;
import UI.LearnerOperations;

import java.util.ArrayList;


public class Learner extends User{
    private final LearnerManager learnerManager = new UserManager() ;

    private final ROLE role = ROLE.LEARNER;
    //******* Constructor ***************************************************************************

    public Learner(String userId,String userName,String password,String firstName){
        super(userId,userName,password,firstName);
    }

    @Override
    public void openDashboard() {
        new LearnerOperations(this).learnerDashBoard();
    }

    //******* Getters and Setters ********************************************************************
    public ROLE getRole() {
        return role;
    }
    public ArrayList<String> getEnrolledCourses(){
        return learnerManager.getEnrolledCourses(this.getUserId());
    }

    public void enrollCourse(String courseId){
        learnerManager.enrollCourse(courseId,this.getUserId());
    }

    public boolean isEnrolled(String courseId) {
        return getEnrolledCourses().contains(courseId);
    }

    //***************************************

    public double getCourseProgress(String courseId) {
        return learnerManager.getCourseProgress(courseId,this.getUserId());
    }

    //***************************************

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
