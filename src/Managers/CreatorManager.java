package Managers;

import Core.Course.Course;

import java.util.ArrayList;

public interface CreatorManager {
    void addNewCourse(Course newCourse);

    void deleteCourse(String courseId, String userId);

    void changeCourseName(String newCourseName,String courseId,String userId);

    void changeCoursePrice(int newPrice, String courseId, String userId);

    void addCourseCategory(String category, String courseId,String userId);

    void removeCourseCategory(String category, String courseId, String userId);

    ArrayList<Course> getCreatedCourse(String userId);
}
