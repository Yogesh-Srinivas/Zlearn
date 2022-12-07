package Core.Users;

import Core.Course.Chapter;
import Managers.AdminManager;
import Managers.UserManager;
import UI.AdminOperations;
import UI.LearnerOperations;

import java.util.ArrayList;

public class Admin extends Creator{
    private final AdminManager adminManager = new UserManager();

    private final ROLE role = ROLE.ADMIN;

    public Admin(String userId,String password,String firstName){
        super(userId,null,password,firstName);
    }

    @Override
    public void openDashboard() {
        new AdminOperations(this).adminDashBoard();
    }
    //******* Getters and Setters ********************************************************************
    public ROLE getRole() {
        return role;
    }

    //***************************************

    public boolean removeLearner(String userName){
        return adminManager.removeLearner(userName);
    }
    public boolean changeLearnerPassword(String newPassword,String userName){
        return adminManager.changeLearnerPassword(newPassword,userName);
    }
    //***************************************
    public boolean removeCreator(String userName){
        return adminManager.removeCreator(userName);
    }
    public boolean changeCreatorPassword(String newPassword,String userName){
        return adminManager.changeCreatorPassword(newPassword,userName);
    }
    //***************************************

    public void removeAdmin(String adminId){
        adminManager.removeAdmin(adminId);
    }
    //***************************************

    public void addCategoryToAllCategories(String newCategory) {
        adminManager.addCategoryToAllCategories(newCategory);
    }

    public void deleteCategoryFromAllCategories(String category) {
        adminManager.deleteCategoryFromAllCategories(category);
    }
    //***************************************

    public void addNewCourse(String courseName, ArrayList<String> selectedCategories, ArrayList<Chapter> courseContent, int coursePrice) {
        creatorManager.addNewCourse(courseName,selectedCategories,courseContent,coursePrice,this.getUserId());
    }

    public boolean removeCourse(String courseId) {
        return creatorManager.deleteCourse(courseId,this.getUserId());
    }
    //***************************************

    public void addCourseContent(String courseId, Chapter newChapter) {
        creatorManager.addCourseContent(courseId,newChapter,this.getUserId());
    }

    public void deleteCourseContent(String courseId, int contentIndex) {
        creatorManager.deleteCourseContent(courseId,contentIndex,this.getUserId());
    }
    //***************************************


    public void changeCourseName(String newCourseName, String courseId) {
        creatorManager.changeCourseName(newCourseName,courseId,this.getUserId());
    }
    public void changeCoursePrice(int coursePrice, String courseId) {
        creatorManager.changeCoursePrice(coursePrice,courseId,this.getUserId());
    }

    //***************************************
    public void changeCourseChapterName(String newChapterName, String courseId, int contentIndex) {
        creatorManager.changeCourseChapterName(newChapterName,courseId,contentIndex,this.getUserId());
    }
    public void changeCourseChapterContent(String newContent, String courseId, int contentIndex) {
        creatorManager.changeCourseChapterContent(newContent,courseId,contentIndex,this.getUserId());
    }

    //***************************************

    public void removeCourseCategory(String categoryToRemove, String courseId) {
        creatorManager.removeCourseCategory(categoryToRemove,courseId,this.getUserId());
    }

    public void addCourseCategory(String catergoryToadd, String courseId) {
        creatorManager.addCourseCategory(catergoryToadd,courseId,this.getUserId());
    }
}
