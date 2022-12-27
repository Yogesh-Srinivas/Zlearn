package Managers;

import Core.Course.Chapter;
import Core.Course.Comment;
import Core.Course.Course;
import Core.Users.Admin;
import Core.Users.User;
import Database.*;
import UI.AuthStatus;

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
        return dbManager.getCategories();
    }

    public ArrayList<String> getCourseCategories(String courseId) {
        return dbManager.getCourseCategories(courseId);
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

    //******** Learner *******************************************************************
    public  AuthStatus learnerAuthentication(String userName, String password){
        return dbManager.learnerAuthentication(userName, password);
    }

    public  User getLearner(String userName) {
        return dbManager.getLearner(userName);
    }

    public  boolean isLearnerUserNameAvailable(String userName) {
        return dbManager.isLearnerUserNameAvailable(userName);
    }

    public  void addLearner(String userName, String password, String firstName) {
        dbManager.addLearner(userName,password,firstName);
    }

    //******* Creator ********************************************************************

    public  AuthStatus creatorAuthentication(String userName, String password){
        return dbManager.creatorAuthentication(userName,password);
    }

    public  User getCreator(String userName) {
        return dbManager.getCreator(userName);
    }

    public  boolean isCreatorUserNameAvailable(String userName) {
        return dbManager.isCreatorUserNameAvailable(userName);
    }

    public  void addCreator(String userName, String password, String firstName) {
        dbManager.addCreator(userName,password,firstName);
    }

    //******* Admin ********************************************************************

    public  AuthStatus adminAuthentication(String adminId, String adminPassword){
        return dbManager.adminAuthentication(adminId,adminPassword);
    }

    public  Admin getAdmin(String adminId) {
        return dbManager.getAdmin(adminId);
    }
}
