package Managers;

import Core.Course.Chapter;
import Core.Course.Comment;
import Core.Course.Course;
import Database.CourseDBOperations;
import Database.CourseDatabase;
import Database.UserDBOperations;
import Database.UserDatabase;

import java.util.ArrayList;

public class UIManager {
    private final CourseDBOperations coursedb = CourseDatabase.getInstance();
    private final UserDBOperations userdb = UserDatabase.getInstance();

    //***************************************************************************

    public String getLearnerName(String commentor) {
        return userdb.getLearnerName(commentor);
    }

    public String getCreatorName(String creatorId) {
        return userdb.getCreatorName(creatorId);
    }

    //***************************************************************************

    public ArrayList<Course> getAllCourses() {
        return new ArrayList<>(coursedb.getAllCourses());
    }

    public Course getCourseDetails(String courseId) {
        return coursedb.getCourse(courseId);
    }

    public ArrayList<String> getCategories() {
        return new ArrayList<>(coursedb.getAllCategories());
    }

    public ArrayList<Course> getCoursesBasedOnCategory(String category) {
        return new ArrayList<>(coursedb.getCoursesBasedOnCategory(category));
    }

    public ArrayList<Chapter> getCourseContent(String courseId) {
        return coursedb.getCourseContent(courseId);
    }

    public ArrayList<String> getCourseLearnings(String courseId) {
        return coursedb.getCourseLearnings(courseId);
    }

    public ArrayList<String> getCourseCategories(String courseId) {
        return coursedb.getCourseCategories(courseId);
    }

    public int getCourseChapterCount(String courseId) {
        return coursedb.getCourseChapterCount(courseId);
    }

    public Chapter getChapter(String courseId,int contentIndex) {
        return coursedb.getChapter(courseId,contentIndex);
    }

    public ArrayList<Comment> getComments(String courseId) {
        return coursedb.getCourseComments(courseId);
    }

    public boolean isRatedBy(String userId, String courseId) {
        return coursedb.isRatedBy(userId,courseId);
    }

    public ArrayList<Chapter> getContent(String courseId) {
        return coursedb.getCourseContent(courseId);
    }
}
