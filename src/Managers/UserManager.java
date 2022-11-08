package Managers;

import Core.Course.Course;
import Database.CourseDatabase;
import Database.UserDatabase;

import java.util.ArrayList;

public class UserManager implements LearnerManager,CreatorManager,AdminManager{

    //***** Learner Manager Operations ******************************************************
    @Override
    public void enrollNewCourse(String courseId, String userId) {
        UserDatabase userdb = UserDatabase.getInstance();
        userdb.addCourseToLearner(userId,courseId);
    }

    @Override
    public void updateCourseProgress(String courseId, double courseProgressStepValue, String userId) {
        UserDatabase userdb = UserDatabase.getInstance();
        double currentProgress = userdb.getLearnerCurrentProgress(courseId,userId);
        currentProgress += courseProgressStepValue;
        if(currentProgress > 99.0) currentProgress=100.0;
        userdb.updateUserProgress(courseId,userId,currentProgress);
    }

    @Override
    public void addComment(String comment, String courseId, String userId) {
        CourseDatabase coursedb = CourseDatabase.getInstance();
        coursedb.addComment(comment,courseId,userId);
    }

    @Override
    public void rateCourse(String courseId, int rating, String userId) {
        CourseDatabase coursedb = CourseDatabase.getInstance();
        coursedb.rateCourse(courseId,rating,userId);
    }
    @Override
    public void unenrollCourse(String courseId, String userId) {
        UserDatabase userdb = UserDatabase.getInstance();
        userdb.unenrollCourse(courseId,userId);
    }
    //***** Creator Manager Operations ******************************************************
    @Override
    public void addNewCourse(Course newCourse) {
        CourseDatabase coursedb = CourseDatabase.getInstance();
        coursedb.addCourse(newCourse);
    }

    @Override
    public void deleteCourse(String courseId, String userId) {
        CourseDatabase coursedb = CourseDatabase.getInstance();
        coursedb.deleteCourse(courseId,userId);
    }

    @Override
    public void changeCourseName(String newCourseName,String courseId,String userId) {
        CourseDatabase coursedb = CourseDatabase.getInstance();
        coursedb.changeCourseName(newCourseName,courseId,userId);
    }

    @Override
    public void changeCoursePrice(int newPrice, String courseId, String userId) {
        CourseDatabase coursedb = CourseDatabase.getInstance();
        coursedb.changeCoursePrice(newPrice,courseId,userId);
    }

    @Override
    public void addCourseCategory(String category, String courseId,String userId) {
        CourseDatabase coursedb = CourseDatabase.getInstance();
        coursedb.addCourseCategory(category,courseId,userId);
    }

    @Override
    public void removeCourseCategory(String category, String courseId, String userId) {
        CourseDatabase coursedb = CourseDatabase.getInstance();
        coursedb.removeCourseCategory(category,courseId,userId);
    }

    @Override
    public ArrayList<Course> getCreatedCourse(String userId) {
        CourseDatabase coursedb = CourseDatabase.getInstance();
        return coursedb.getCreatedCourses(userId);
    }

    //***** Admin Manager Operations ******************************************************
    @Override
    public void removeLearner(String userId) {
        UserDatabase userdb = UserDatabase.getInstance();
        userdb.removeLearner(userId);
    }
    @Override
    public void removeCreator(String userId) {
        UserDatabase userdb = UserDatabase.getInstance();
        userdb.removeCreator(userId);
    }
    @Override
    public void removeAdmin(String adminId) {
        UserDatabase userdb = UserDatabase.getInstance();
        userdb.removeAdmin(adminId);
    }

    @Override
    public void changeLearnerPassword(String newPassword, String userId) {
        UserDatabase userdb = UserDatabase.getInstance();
        userdb.changeLearnerPassword(userId,newPassword);
    }

    @Override
    public void changeCreatorPassword(String newPassword, String userId) {
        UserDatabase userdb = UserDatabase.getInstance();
        userdb.changeCreatorPassword(userId,newPassword);
    }


}
