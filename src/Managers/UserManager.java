package Managers;

import Core.Course.Chapter;
import Core.Course.Comment;
import Core.Course.Course;
import Database.CourseDatabase;
import Database.UserDatabase;

import java.util.ArrayList;

public class UserManager implements LearnerManager,CreatorManager,AdminManager{
    private final  UserDatabase userdb = UserDatabase.getInstance();
    private final CourseDatabase coursedb = CourseDatabase.getInstance();

    //***** Learner Manager Operations ******************************************************
    @Override
    public void enrollNewCourse(String courseId, String userId) {
        userdb.addCourseToLearner(userId,courseId);
    }

    @Override
    public void updateCourseProgress(String courseId, double courseProgressStepValue, String userId) {
        double currentProgress = userdb.getLearnerCurrentProgress(courseId,userId);
        currentProgress += courseProgressStepValue;
        if(currentProgress > 99.0) currentProgress=100.0;
        userdb.updateUserProgress(courseId,userId,currentProgress);
    }

    @Override
    public void addComment(String comment, String courseId, String userId) {
        coursedb.addComment(comment,courseId,userId);
    }

    @Override
    public void rateCourse(String courseId, int rating, String userId) {
        coursedb.rateCourse(courseId,rating,userId);
    }
    @Override
    public void unenrollCourse(String courseId, String userId) {
        userdb.unenrollCourse(courseId,userId);
    }
    //***** Creator Manager Operations ******************************************************
    @Override
    public void addNewCourse(String courseName, ArrayList<String> courseCategories, ArrayList<Chapter> courseContent, int coursePrice,String creatorId) {
        String courseId = IdGenerator.getNewGeneralCourseId();
        Course newCourse = new Course(courseName,courseId,courseCategories,creatorId,coursePrice,courseContent);
        coursedb.addCourse(newCourse);
    }

    @Override
    public void addCourseContent(String courseId, Chapter courseChapter, String userId) {
        coursedb.addCourseContent(courseId,courseChapter,userId);
    }

    @Override
    public void deleteCourseContent(String courseId, int contentIndex, String userId) {
        coursedb.deleteCourseContent(courseId,contentIndex,userId);
    }

    @Override
    public void changeCourseChapterName(String newChapterName, String courseId, int contentIndex, String userId) {
        coursedb.changeCourseChapterName(newChapterName,courseId,contentIndex,userId);
    }

    @Override
    public void changeCourseChapterContent(String newContent, String courseId, int contentIndex, String userId) {
        coursedb.changeCourseLesson(newContent,courseId,contentIndex,userId);
    }

    @Override
    public void deleteCourse(String courseId, String userId) {
        coursedb.deleteCourse(courseId,userId);
        //unenroll all users from deleterd course
        UserDatabase userdb = UserDatabase.getInstance();
        ArrayList<String> courseLearners = userdb.getCourseLearners(courseId);
        if(courseLearners.size()!=0){
            for(String learnerId : courseLearners)
                userdb.unenrollCourse(courseId,learnerId);
        }
    }

    @Override
    public void changeCourseName(String newCourseName,String courseId,String userId) {
        coursedb.changeCourseName(newCourseName,courseId,userId);
    }

    @Override
    public void changeCoursePrice(int newPrice, String courseId, String userId) {
        coursedb.changeCoursePrice(newPrice,courseId,userId);
    }

    @Override
    public void addCourseCategory(String category, String courseId,String userId) {
        coursedb.addCourseCategory(category,courseId,userId);
    }

    @Override
    public void removeCourseCategory(String category, String courseId, String userId) {
        coursedb.removeCourseCategory(category,courseId,userId);
    }

    @Override
    public ArrayList<Course> getCreatedCourse(String userId) {
        return coursedb.getCreatedCourses(userId);
    }

    @Override
    public ArrayList<Comment> getCourseComments(String courseId, String userId) {
        return coursedb.getCourseComments(courseId,userId);
    }

    //***** Admin Manager Operations ******************************************************
    @Override
    public void removeLearner(String userName) {
        userdb.removeLearner(userName);
    }
    @Override
    public void removeCreator(String userName) {
        userdb.removeCreator(userName);
    }
    @Override
    public void removeAdmin(String adminId) {
        userdb.removeAdmin(adminId);
    }

    @Override
    public void changeLearnerPassword(String newPassword, String userName) {
        userdb.changeLearnerPassword(userName,newPassword);
    }

    @Override
    public void changeCreatorPassword(String newPassword, String userName) {
        userdb.changeCreatorPassword(userName,newPassword);
    }

    @Override
    public void addCategoryToAllCategories(String newCategory) {
        coursedb.addToAllCategories(newCategory);
    }

    @Override
    public void deleteCategoryFromAllCategories(String category) {
        coursedb.removeFromAllCategories(category);
    }

    @Override
    public void removeCourse(String courseId,String adminId) {
        coursedb.deleteCourse(courseId,adminId);
    }

    @Override
    public void addNewZCourse(String courseName, ArrayList<String> selectedCategories,
                              ArrayList<Chapter> courseContent, int coursePrice, String adminId) {
        String courseId = IdGenerator.getNewZlearnCourseId();
        coursedb.addCourse(new Course(courseName,courseId,selectedCategories,courseId,coursePrice,courseContent));
    }


}
