package Managers;

import Core.Course.Course;
import Database.CourseDatabase;
import Database.UserDatabase;

import java.util.ArrayList;

public class UIManager {
    CourseDatabase coursedb = CourseDatabase.getInstance();
    UserDatabase userdb = UserDatabase.getInstance();

    public Course getCourseDetails(String courseId) {
        return coursedb.getCourse(courseId);
    }

    public String getCreatorName(String creatorId) {
        return userdb.getCreatorName(creatorId);
    }

    public String getLearnerName(String commentor) {
        return userdb.getLearnerName(commentor);
    }

    public ArrayList<String> getCategories() {
        return coursedb.getAllCategories();
    }

    public ArrayList<Course> getCoursesBasedOnCategory(String category) {
        return coursedb.getCoursesBasedOnCategory(category);
    }

    public ArrayList<Course> getAllCourses() {
        return coursedb.getAllCourses();
    }
}
