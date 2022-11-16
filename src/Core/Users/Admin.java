package Core.Users;

import Core.Course.Chapter;
import Managers.AdminManager;
import Managers.UserManager;

import java.util.ArrayList;

public class Admin{
    private final String adminId;
    private String adminPassword;
    private final AdminManager adminManager = new UserManager();

    //******* Constructor ***************************************************************************
    public Admin(String adminId, String adminPassword) {
        this.adminId = adminId;
        this.adminPassword = adminPassword;
    }
    //******* Getters and Setters ********************************************************************
    public String getAdminId() {
        return adminId;
    }

    //***************************************

    public boolean isCorrectPassword(String adminPassword) {
        return this.adminPassword.equals(adminPassword);
    }

    public void setAdminPassword(String newPassword) { this.adminPassword = newPassword;}
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
        adminManager.addNewZCourse(courseName,selectedCategories,courseContent,coursePrice,this.adminId);
    }

    public boolean removeCourse(String courseId) {
        return adminManager.removeCourse(courseId,this.getAdminId());
    }
    //***************************************

    public void addCourseContent(String courseId, Chapter newChapter) {
        adminManager.addCourseContent(courseId,newChapter,this.adminId);
    }

    public void deleteCourseContent(String courseId, int contentIndex) {
        adminManager.deleteCourseContent(courseId,contentIndex,this.adminId);
    }
    //***************************************


    public void changeCourseName(String newCourseName, String courseId) {
        adminManager.changeCourseName(newCourseName,courseId,this.adminId);
    }
    public void changeCoursePrice(int coursePrice, String courseId) {
        adminManager.changeCoursePrice(coursePrice,courseId,this.adminId);
    }

    //***************************************
    public void changeCourseChapterName(String newChapterName, String courseId, int contentIndex) {
        adminManager.changeCourseChapterName(newChapterName,courseId,contentIndex,this.adminId);
    }
    public void changeCourseChapterContent(String newContent, String courseId, int contentIndex) {
        adminManager.changeCourseChapterContent(newContent,courseId,contentIndex,this.adminId);
    }

    //***************************************

    public void removeCourseCategory(String categoryToRemove, String courseId) {
        adminManager.removeCourseCategory(categoryToRemove,courseId,this.adminId);
    }

    public void addCourseCategory(String catergoryToadd, String courseId) {
        adminManager.addCourseCategory(catergoryToadd,courseId,this.adminId);
    }
}
