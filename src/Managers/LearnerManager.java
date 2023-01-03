package Managers;

import java.util.ArrayList;

public interface LearnerManager {

    //******* enroll course ******

    ArrayList<String> getEnrolledCourses(String userId);

    void enrollCourse(String courseId, String userId);


    //******* course Progress ******

    int getCourseProgress(String courseId,String userId);

    void updateCourseProgress(String courseId, String userID);

    //******* add comment ******

    void addComment(String comment, String courseId, String userID);

    //******* rate course ******

    void rateCourse(String courseId, int rating, String userID);

    //******* unenroll course ******

    void unenrollCourse(String courseId, String userId);
}
