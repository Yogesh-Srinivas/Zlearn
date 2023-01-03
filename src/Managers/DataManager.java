package Managers;

import Core.Course.Chapter;
import Core.Course.Comment;
import Core.Course.Course;
import Core.Users.User;
import Database.*;

import java.util.ArrayList;

public class DataManager {
    private final DatabaseManager dbManager = new DatabaseManager();

    //******* Users ************************

    public String getLearnerName(String commentor) {
        return dbManager.getLearnerName(commentor);
    }

    public String getCreatorName(String creatorId) {
        return dbManager.getCreatorName(creatorId);
    }

    //******* Course ************************


    public ArrayList<Course> getAllCourses() {
        return dbManager.getAllCourses();
    }

    public Course getCourseDetails(String courseId) {
        return dbManager.getCourseDetails(courseId);
    }

    //******* Course Category ************************

    public ArrayList<String> getCategories() {
        ArrayList<String> categories = dbManager.getCategories();
        categories.remove("General");
        return categories;
    }

    public ArrayList<String> getCourseCategories(String courseId) {
        ArrayList<String> courseCategories = dbManager.getCourseCategories(courseId);
        courseCategories.remove("General");
        return courseCategories;
    }

    public ArrayList<Course> getCoursesBasedOnCategory(String category) {
        return dbManager.getCoursesBasedOnCategory(category);
    }

    //******* Course Content ************************

    public ArrayList<Chapter> getCourseContent(String courseId) {
        return dbManager.getCourseContent(courseId);
    }

    public ArrayList<String> getCourseLearnings(String courseId) {
        return dbManager.getCourseLearnings(courseId);
    }

    public int getCourseChapterCount(String courseId) {
        return dbManager.getCourseChapterCount(courseId);
    }

    public Chapter getChapter(String courseId,int contentIndex) {
        return dbManager.getChapter(courseId,contentIndex);
    }

    //******* Course Comments *************************

    public ArrayList<Comment> getComments(String courseId) {
        return dbManager.getCourseComments(courseId);
    }

    //******* Rating ************************

    public boolean isRatedBy(String userId, String courseId) {
        return dbManager.isRatedBy(userId,courseId);
    }


    //******** User *******************************************************************
    public  AuthStatus userAuthentication(String userName, String password){
        return dbManager.userAuthentication(userName, password);
    }

    public  User getUser(String userName) {
        return dbManager.getUser(userName);
    }

    public  boolean isUserNameAvailable(String userName) {
        return dbManager.isUserNameAvailable(userName);
    }

    //******** Learner *******************************************************************

    public  void addLearner(String userName, String password, String firstName) {
        dbManager.addLearner(userName,password,firstName);
    }

    //******* Creator ********************************************************************

    public  void addCreator(String userName, String password, String firstName) {
        dbManager.addCreator(userName,password,firstName);
    }
}
