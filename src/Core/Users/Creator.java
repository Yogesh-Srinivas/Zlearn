package Core.Users;

import Core.Course.Chapter;
import Core.Course.Comment;
import Core.Course.Course;
import Managers.CreatorManager;
import Managers.UserManager;
import UI.CreatorOperations;
import UI.LearnerOperations;

import java.util.ArrayList;

public class Creator extends User{
    protected final CreatorManager creatorManager = new UserManager();
    private final ROLE role = ROLE.CREATOR;

    //******* Constructor ***************************************************************************

    public Creator(String userId,String userName,String password,String firstName){
        super(userId,userName,password,firstName);
    }

    @Override
    public void openDashboard() {
        new CreatorOperations(this).creatorDashBoard();
    }
    //******* Getters and Setters ********************************************************************
    public ROLE getRole() {
        return role;
    }

    public ArrayList<Comment> getCourseComments(String courseId) {
        return  new ArrayList<>(creatorManager.getCourseComments(courseId,this.getUserId()));
    }

    public ArrayList<Course> getCreatedCourse(){
        return new ArrayList<>(creatorManager.getCreatedCourse(this.getUserId()));
    }

    //***************************************

    public void addNewCourse(String courseName, ArrayList<String> courseCategories, ArrayList<Chapter> courseContent, int coursePrice) {
        creatorManager.addNewCourse(courseName,courseCategories,courseContent,coursePrice,this.getUserId());
    }

    public void deleteCourse(String courseId){
        creatorManager.deleteCourse(courseId,this.getUserId());
    }

    //***************************************

    public void changeCourseName(String newCourseName,String courseId){
        creatorManager.changeCourseName(newCourseName,courseId,this.getUserId());
    }

    public void changeCoursePrice(int newPrice,String courseId){
        creatorManager.changeCoursePrice(newPrice,courseId,this.getUserId());
    }

    //***************************************

    public void addCourseCategory(String category,String courseId){
        creatorManager.addCourseCategory(category,courseId,this.getUserId());
    }

    public void removeCourseCategory(String category,String courseId){
        creatorManager.removeCourseCategory(category,courseId,this.getUserId());
    }

    //***************************************

    public void addCourseContent(String courseId, Chapter courseChapter) {
        creatorManager.addCourseContent(courseId,courseChapter,this.getUserId());
    }

    public void deleteCourseContent(String courseId, int contentIndex) {
        creatorManager.deleteCourseContent(courseId,contentIndex,this.getUserId());
    }

    //***************************************

    public void changeCourseChapterName(String newChapterName, String courseId, int contentIndex) {
        creatorManager.changeCourseChapterName(newChapterName,courseId,contentIndex,this.getUserId());
    }

    public void changeCourseChapterContent(String newContent, String courseId, int contentIndex) {
        creatorManager.changeCourseChapterContent(newContent,courseId,contentIndex,this.getUserId());
    }

    //***************************************

}
