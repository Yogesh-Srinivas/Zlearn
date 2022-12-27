package Managers;

import Core.Course.Chapter;
import Core.Course.Comment;
import Core.Course.Course;
import Core.Course.CourseProgress;
import Database.*;

import java.util.ArrayList;

public class UserManager implements LearnerManager,CreatorManager,AdminManager{
    private final DatabaseManager dbManager = new DatabaseManager();

    //***** Learner Manager Operations ********************************************************************************
    //******* enroll course ******
    @Override
    public ArrayList<String> getEnrolledCourses(String userId) {
        return dbManager.getEnrolledCourses(userId);
    }

    @Override
    public void enrollCourse(String courseId, String userId) {
        dbManager.enrollCourse(new CourseProgress(userId,courseId,0.0));
    }

    //******* course Progress ******
    @Override
    public double getCourseProgress(String courseId,String userId) {
        return dbManager.getCourseProgress(courseId,userId);
    }
    @Override
    public void updateCourseProgress(String courseId, double courseProgressStepValue, String userId) {
        double currentProgress = dbManager.getCourseProgress(courseId,userId);
        currentProgress += courseProgressStepValue;
        if(currentProgress > 99.0) currentProgress=100.0;
        dbManager.updateCourseProgress(courseId,userId,currentProgress);
    }

    //******* add comment ******
    @Override
    public void addComment(String comment, String courseId, String userId) {
        dbManager.addComment(comment,courseId,userId);
    }

    //******* rate course ******

    @Override
    public void rateCourse(String courseId, int rating, String userId) {
        dbManager.rateCourse(courseId,rating,userId);
    }


    //******* unenroll course ******
    @Override
    public void unenrollCourse(String courseId, String userId) {
        dbManager.unenrollCourse(courseId,userId);
    }

    //***** Creator Manager Operations *********************************************************************************

    //******* course ******
    @Override
    public void addNewCourse(String courseName, ArrayList<String> courseCategories, ArrayList<Chapter> content, int coursePrice,String creatorId) {
        dbManager.addNewCourse(courseName, courseCategories, content,coursePrice,creatorId);
    }

    @Override
    public boolean deleteCourse(String courseId, String userId) {
        return dbManager.deleteCourse(courseId,userId);
    }
    @Override
    public ArrayList<Course> getCreatedCourse(String userId) {
        return dbManager.getCreatedCourse(userId);
    }


    //******* course comemnt ******
    @Override
    public void changeCourseName(String newCourseName,String courseId,String userId) {
        dbManager.changeCourseName(newCourseName,courseId,userId);
    }

    @Override
    public ArrayList<Comment> getCourseComments(String courseId) {
        return dbManager.getCourseComments(courseId);
    }
    //******* course price ******

    @Override
    public void changeCoursePrice(int newPrice, String courseId, String userId) {
        dbManager.changeCoursePrice(newPrice,courseId,userId);
    }
    //******* course category ******
    @Override
    public void addCourseCategory(String category, String courseId,String userId) {
        dbManager.addCourseCategory(category,courseId,userId);
    }

    @Override
    public void removeCourseCategory(String category, String courseId, String userId) {
        dbManager.removeCourseCategory(category,courseId,userId);
    }
    //******* course content ******

    @Override
    public void addCourseContent(String courseId, Chapter courseChapter, String userId) {
        courseChapter.setCourseId(courseId);
        dbManager.addCourseContent(courseId,courseChapter,userId);
    }

    @Override
    public void deleteCourseContent(String courseId, int lessonNo, String userId) {
        dbManager.deleteCourseContent(courseId,lessonNo,userId);
    }

    //********** course chapter *************

    @Override
    public void changeCourseChapterName(String newChapterName, String courseId, int lessonNo, String userId) {
        dbManager.changeCourseChapterName(newChapterName,courseId,lessonNo,userId);
    }

    @Override
    public void changeCourseChapterContent(String newContent, String courseId, int lessonNo, String userId) {
        dbManager.changeCourseChapterContent(newContent,courseId,lessonNo,userId);
    }

    //***** Admin Manager Operations **************************************************************************************

    //******* Learner *********************
    @Override
    public boolean removeLearner(String userName) {
        return dbManager.removeLearner(userName);
    }
    @Override
    public boolean changeLearnerPassword(String newPassword, String userName) {
        return dbManager.changeLearnerPassword(newPassword,userName);
    }

    //******* Creator ************************
    @Override
    public boolean removeCreator(String userName) {
        return dbManager.removeCreator(userName);
    }

    @Override
    public boolean changeCreatorPassword(String newPassword, String userName) {
        return dbManager.changeCreatorPassword(newPassword,userName);
    }

    //******* Admin operation ************************
    @Override
    public void removeAdmin(String adminId) {
        dbManager.removeAdmin(adminId);
    }

    //******* Zlearn Category operation ************************
    @Override
    public void addCategoryToAllCategories(String newCategory) {
        dbManager.addCategoryToAllCategories(newCategory);
    }

    @Override
    public void deleteCategoryFromAllCategories(String category) {
        dbManager.deleteCategoryFromAllCategories(category);
    }

}
