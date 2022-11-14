package Managers;

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
}
