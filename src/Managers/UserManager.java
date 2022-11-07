package Managers;

import Database.CourseDatabase;
import Database.UserDatabase;

public class UserManager implements LearnerManager,CreatorManager,AdminManager{
    public UserManager initialize(){
        return new UserManager();
    }
    //***** Learner Manager Operations ******************************************************
    @Override
    public void enrollNewCourse(String courseId, String userId) {
        UserDatabase userdb = UserDatabase.getInstance();
        userdb.addCourseToLearner(userId,courseId);
    }

    @Override
    public void updateCourseProgress(String courseId, double currentProgress, String userId) {
        UserDatabase userdb = UserDatabase.getInstance();
        userdb.updateUserProgress(userId,currentProgress,courseId);
    }

    @Override
    public void addComment(String comment, String courseId, String userId) {
        CourseDatabase coursedb = CourseDatabase.getInstance();
        coursedb.addComment(comment,courseId,userId);
    }

    @Override
    public void rateCourse(String courseId, int rating, String userId) {
        CourseDatabase coursedb = CourseDatabase.getInstance();
        coursedb.rateCourse(courseId,rating,userId);
    }

    @Override
    public void unenrollCourse(String courseId, String userId) {
        UserDatabase userdb = UserDatabase.getInstance();
        userdb.unenrollCourse(courseId,userId);
    }
}
