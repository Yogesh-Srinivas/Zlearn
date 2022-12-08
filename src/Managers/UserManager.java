package Managers;

import Core.Course.Chapter;
import Core.Course.Comment;
import Core.Course.Course;
import Core.Course.CourseProgress;
import Database.CourseDBOperations;
import Database.CourseDatabase;
import Database.UserDBOperations;
import Database.UserDatabase;

import java.util.ArrayList;

public class UserManager implements LearnerManager,CreatorManager,AdminManager{
    private final UserDBOperations userdb = UserDatabase.getInstance();
    private final CourseDBOperations coursedb = CourseDatabase.getInstance();

    //***** Learner Manager Operations ******************************************************

    @Override
    public void updateCourseProgress(String courseId, double courseProgressStepValue, String userId) {
        double currentProgress = coursedb.getCourseProgress(courseId,userId);
        currentProgress += courseProgressStepValue;
        if(currentProgress > 99.0) currentProgress=100.0;
        coursedb.updateProgress(courseId,userId,currentProgress);
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
        coursedb.unenrollCourse(courseId,userId);
    }

    @Override
    public ArrayList<String> getEnrolledCourses(String userId) {
        return coursedb.getEnrolledCourses(userId);
    }

    @Override
    public void enrollCourse(String courseId, String userId) {
        coursedb.enrollCourse(new CourseProgress(userId,courseId,0.0));
    }

    @Override
    public double getCourseProgress(String courseId,String userId) {
        return coursedb.getCourseProgress(courseId,userId);
    }

    //***** Creator Manager Operations ******************************************************
    @Override
    public void changeCourseName(String newCourseName,String courseId,String userId) {
        coursedb.changeCourseName(newCourseName,courseId,userId);
    }

    @Override
    public void changeCoursePrice(int newPrice, String courseId, String userId) {
        coursedb.changeCoursePrice(newPrice,courseId,userId);
    }
    //**************************************

    @Override
    public void addNewCourse(String courseName, ArrayList<String> courseCategories, ArrayList<Chapter> content, int coursePrice,String creatorId) {
        String courseId;
        ArrayList<Chapter> courseContent = new ArrayList<>(content);
        if(creatorId.contains("Adm"))  courseId = IdGenerator.getNewZlearnCourseId();
        else  courseId = IdGenerator.getNewGeneralCourseId();
        //replacing null with courseId in courseContent
        for(int chInd=0;chInd<courseContent.size();chInd++){
            courseContent.get(chInd).setCourseId(courseId);
        }
        coursedb.addCourse(new Course(courseName,courseId,creatorId,coursePrice,courseContent.size()),courseContent);
        for (String category:courseCategories)
            coursedb.addCourseCategory(category,courseId,creatorId);
    }

    @Override
    public boolean deleteCourse(String courseId, String userId) {
        //unenroll all users from deleted course
        UserDatabase userdb = UserDatabase.getInstance();
        ArrayList<String> courseLearners = coursedb.getCourseLearners(courseId);
        if(courseLearners.size()!=0){
            for(String learnerId : courseLearners)
                coursedb.unenrollCourse(courseId,learnerId);
        }
        //delete course comments
        coursedb.deleteCourseComments(courseId,userId);
        //delete course chapters
        coursedb.deleteCourseContent(courseId,userId);
        //delete Rated by
        coursedb.deleteCourseRatedBy(courseId,userId);
        //delete course category
        coursedb.deleteCourseCategory(courseId,userId);
        return coursedb.deleteCourse(courseId,userId);
    }
    //**************************************

    @Override
    public void addCourseContent(String courseId, Chapter courseChapter, String userId) {
        courseChapter.setCourseId(courseId);
        coursedb.addCourseContent(courseId,courseChapter,userId);
    }

    @Override
    public void deleteCourseContent(String courseId, int lessonNo, String userId) {
        coursedb.deleteCourseContent(courseId,lessonNo,userId);
    }
    //**************************************
    @Override
    public void changeCourseChapterName(String newChapterName, String courseId, int lessonNo, String userId) {
        coursedb.changeCourseChapterName(newChapterName,courseId,lessonNo,userId);
    }

    @Override
    public void changeCourseChapterContent(String newContent, String courseId, int lessonNo, String userId) {
        coursedb.changeCourseLesson(newContent,courseId,lessonNo,userId);
    }

    //**************************************

    @Override
    public void addCourseCategory(String category, String courseId,String userId) {
        coursedb.addCourseCategory(category,courseId,userId);
    }

    @Override
    public void removeCourseCategory(String category, String courseId, String userId) {
        coursedb.removeCourseCategory(category,courseId,userId);
    }
    //**************************************

    @Override
    public ArrayList<Course> getCreatedCourse(String userId) {
        return coursedb.getCreatedCourses(userId);
    }

    @Override
    public ArrayList<Comment> getCourseComments(String courseId) {
        return coursedb.getCourseComments(courseId);
    }

    //***** Admin Manager Operations ******************************************************
    @Override
    public boolean removeLearner(String userName) {
        return userdb.removeLearner(userName);
    }
    @Override
    public boolean changeLearnerPassword(String newPassword, String userName) {
        return userdb.changeLearnerPassword(userName,newPassword);
    }
    //**************************************
    @Override
    public boolean removeCreator(String userName) {
        return userdb.removeCreator(userName);
    }

    @Override
    public boolean changeCreatorPassword(String newPassword, String userName) {
        return userdb.changeCreatorPassword(userName,newPassword);
    }
    //**************************************

    @Override
    public void removeAdmin(String adminId) {
        userdb.removeAdmin(adminId);
    }
    //**************************************

    @Override
    public void addCategoryToAllCategories(String newCategory) {
        coursedb.addToAllCategories(newCategory);
    }

    @Override
    public void deleteCategoryFromAllCategories(String category) {
        coursedb.removeFromAllCategories(category);
    }
    //**************************************



}
