package Managers;

import Core.Course.Course;
import Database.CourseDatabase;
import Database.UserDatabase;

import java.util.ArrayList;

public class UIManager {

    public Course getCourseDetails(String courseId) {
        CourseDatabase coursedb = CourseDatabase.getInstance();
        return coursedb.getCourse(courseId);
    }

    public String getCreatorName(String creatorId) {
        UserDatabase userdb = UserDatabase.getInstance();
        return userdb.getCreatorName(creatorId);
    }

    public String getLearnerName(String commentor) {
        UserDatabase userdb = UserDatabase.getInstance();
        return userdb.getLearnerName(commentor);
    }

    public ArrayList<String> getCategories() {
        CourseDatabase coursedb = CourseDatabase.getInstance();
        return coursedb.getAllCategories();
    }

    public ArrayList<Course> getCoursesBasedOnCategory(String category) {
        CourseDatabase coursedb = CourseDatabase.getInstance();
        return coursedb.getCoursesBasedOnCategory(category);
    }
}
