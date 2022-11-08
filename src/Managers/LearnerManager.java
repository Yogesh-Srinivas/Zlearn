package Managers;

public interface LearnerManager {
    void enrollNewCourse(String courseId, String userID);

    void updateCourseProgress(String courseId, double currentProgress, String userID);

    void addComment(String comment, String courseId, String userID);

    void rateCourse(String courseId, int rating, String userID);

    void unenrollCourse(String courseId, String userId);
}
