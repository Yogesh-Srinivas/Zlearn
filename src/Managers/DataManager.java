package Managers;

import Core.Course.Chapter;
import Core.Course.Comment;
import Core.Course.Course;
import Database.CourseDBOperations;
import Database.CourseDatabase;
import Database.UserDBOperations;
import Database.UserDatabase;

import java.util.ArrayList;

public class DataManager {
    private final CourseDBOperations coursedb = CourseDatabase.getInstance();
    private final UserDBOperations userdb = UserDatabase.getInstance();

    //******* Users ************************

    public String getLearnerName(String commentor) {
        return userdb.getLearnerName(commentor);
    }

    public String getCreatorName(String creatorId) {
        return userdb.getCreatorName(creatorId);
    }

    //******* Course ************************


    public ArrayList<Course> getAllCourses() {
        return new ArrayList<>(coursedb.getAllCourses());
    }

    public Course getCourseDetails(String courseId) {
        return coursedb.getCourse(courseId);
    }

    //******* Course Category ************************

    public ArrayList<String> getCategories() {
        return new ArrayList<>(coursedb.getAllCategories());
    }

    public ArrayList<String> getCourseCategories(String courseId) {
        return coursedb.getCourseCategories(courseId);
    }

    public ArrayList<Course> getCoursesBasedOnCategory(String category) {
        return new ArrayList<>(coursedb.getCoursesBasedOnCategory(category));
    }

    //******* Course Content ************************

    public ArrayList<Chapter> getCourseContent(String courseId) {
        return coursedb.getCourseContent(courseId);
    }

    public ArrayList<String> getCourseLearnings(String courseId) {
        return coursedb.getCourseLearnings(courseId);
    }

    public int getCourseChapterCount(String courseId) {
        return coursedb.getCourseChapterCount(courseId);
    }

    public Chapter getChapter(String courseId,int contentIndex) {
        return coursedb.getChapter(courseId,contentIndex);
    }

    //******* Course Comments *************************

    public ArrayList<Comment> getComments(String courseId) {
        return coursedb.getCourseComments(courseId);
    }

    //******* Rating ************************

    public boolean isRatedBy(String userId, String courseId) {
        return coursedb.isRatedBy(userId,courseId);
    }
}
