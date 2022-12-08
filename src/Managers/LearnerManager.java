package Managers;

import java.util.ArrayList;

public interface LearnerManager {
    void updateCourseProgress(String courseId, double currentProgress, String userID);

    void addComment(String comment, String courseId, String userID);

    void rateCourse(String courseId, int rating, String userID);

    void unenrollCourse(String courseId, String userId);

    ArrayList<String> getEnrolledCourses(String userId);

    void enrollCourse(String courseId, String userId);

    double getCourseProgress(String courseId,String userId);
}
