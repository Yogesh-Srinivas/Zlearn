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

    //Copy Constructor
    public Learner(Learner learner){
        super(learner);
    }

    //******* Getters and Setters ********************************************************************
    public ROLE getRole() {
        return role;
    }

    @Override
    public void openDashboard() {
        new LearnerOperations(this).learnerDashBoard();
    }

    //******* enroll course ******
    public ArrayList<String> getEnrolledCourses(){
        return learnerManager.getEnrolledCourses(this.getUserId());
    }

    public void enrollCourse(String courseId){
        learnerManager.enrollCourse(courseId,this.getUserId());
    }

    //******* course Progress ******

    public double getCourseProgress(String courseId) {
        return learnerManager.getCourseProgress(courseId,this.getUserId());
    }

    public void updateCourseProgress(String courseId,double currentProgress){
        learnerManager.updateCourseProgress(courseId,currentProgress,this.getUserId());
    }

    //******* add comment ******

    public void addComment(String comment,String courseId){
        learnerManager.addComment(comment,courseId,this.getUserId());
    }

    //******* rate course ******

    public void rateCourse(String courseId,int rating){
        learnerManager.rateCourse(courseId,rating,this.getUserId());
    }

    //******* unenroll course ******

    public void unenrollCourse(String courseId){
        learnerManager.unenrollCourse(courseId,this.getUserId());
    }

}
