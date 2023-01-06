package core.users;
import managers.LearnerManager;
import managers.UserManager;
import ui.LearnerOperations;

import java.util.ArrayList;


public class Learner extends User{
    private final LearnerManager learnerManager = new UserManager() ;

    private final ROLE role = ROLE.LEARNER;


    //******* Constructor ***************************************************************************

    public Learner(String userId,String userName,String password,String firstName){
        super(userId,userName,password,firstName);
    }

    //Copy Constructor
    private Learner(Learner learner){
        super(learner);
    }

    public User copy(){
        return new Learner(this);
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

    public int getCourseProgress(String courseId) {
        return learnerManager.getCourseProgress(courseId,this.getUserId());
    }

    public void updateCourseProgress(String courseId){
        learnerManager.updateCourseProgress(courseId,this.getUserId());
    }

    //******* add comment ******

    public void addComment(String comment,String courseId){
        learnerManager.addComment(comment,courseId,this.getUserId());
    }

    //******* rate course ******

    public void rateCourse(String courseId,int rating){
        learnerManager.rateCourse(courseId,rating,this.getUserId());
    }

    //******* un-enroll course ******

    public void unenrollCourse(String courseId){
        learnerManager.unenrollCourse(courseId,this.getUserId());
    }

}
