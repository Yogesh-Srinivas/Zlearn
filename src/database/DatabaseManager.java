package database;

import core.course.Chapter;
import core.course.Comment;
import core.course.Course;
import core.course.CourseProgress;
import core.users.Admin;
import core.users.Creator;
import core.users.Learner;
import core.users.User;
import managers.IdGenerator;
import ui.AuthStatus;

import java.util.ArrayList;

public final class DatabaseManager {
    private final UserDBOperations userdb = UserDatabase.getInstance();
    private final CourseDBOperations coursedb = CourseDatabase.getInstance();

    //***** Learner Manager Operations ********************************************************************************
    //******* enroll course ******
    
    public ArrayList<String> getEnrolledCourses(String userId) {
        return coursedb.getEnrolledCourses(userId);
    }

    
    public void enrollCourse(CourseProgress courseProgress) {
        coursedb.enrollCourse(courseProgress);
    }

    //******* course Progress ******
    
    public int getCourseProgress(String courseId,String userId) {
        return coursedb.getCourseProgress(courseId,userId);
    }
    
    public void updateCourseProgress(String courseId,String userId) {
        coursedb.updateProgress(courseId,userId);
    }

    //******* add comment ******
    
    public void addComment(String comment, String courseId, String userId) {
        coursedb.addComment(comment,courseId,userId);
    }

    //******* rate course ******

    
    public void rateCourse(String courseId, int rating, String userId) {
        coursedb.rateCourse(courseId,rating,userId);
    }


    //******* unenroll course ******
    
    public void unenrollCourse(String courseId, String userId) {
        coursedb.unenrollCourse(courseId,userId);
    }

    //***** Creator Manager Operations *********************************************************************************

    //******* course ******
    
    public void addNewCourse(String courseName, ArrayList<String> courseCategories, ArrayList<Chapter> content, int coursePrice, String creatorId) {
        String courseId;
        ArrayList<Chapter> courseContent = new ArrayList<>(content);
        if(creatorId.contains("Adm"))  courseId = IdGenerator.getNewZlearnCourseId();
        else  courseId = IdGenerator.getNewGeneralCourseId();
        //replacing null with courseId in courseContent
        for (Chapter chapter : courseContent) {
            chapter.setCourseId(courseId);
        }
        coursedb.addCourse(new Course(courseName, courseId, creatorId, coursePrice, courseContent.size()), courseContent);
        for (String category:courseCategories)
            coursedb.addCourseCategory(category,courseId,creatorId);
    }

    
    public boolean deleteCourse(String courseId, String userId) {
        //unenroll all users from deleted course
        ArrayList<String> courseLearners = coursedb.getCourseLearners(courseId);
        if(courseLearners.size()!=0){
            for(String learnerId : courseLearners)
                coursedb.unenrollCourse(courseId,learnerId);
        }
        //delete course comments
        coursedb.deleteCourseComments(courseId,userId);
        //delete course chapters
        coursedb.deleteCourseContent(courseId,userId);
        //delete Rated by
        coursedb.deleteCourseRatedBy(courseId,userId);
        //delete course category
        coursedb.deleteCourseCategory(courseId,userId);
        //delete course
        return coursedb.deleteCourse(courseId,userId);
    }
    
    public ArrayList<Course> getCreatedCourse(String userId) {
        return coursedb.getCreatedCourses(userId);
    }


    //******* course comment ******
    
    public void changeCourseName(String newCourseName,String courseId,String userId) {
        coursedb.changeCourseName(newCourseName,courseId,userId);
    }

    
    public ArrayList<Comment> getCourseComments(String courseId) {
        return coursedb.getCourseComments(courseId);
    }
    //******* course price ******

    
    public void changeCoursePrice(int newPrice, String courseId, String userId) {
        coursedb.changeCoursePrice(newPrice,courseId,userId);
    }
    //******* course category ******
    
    public void addCourseCategory(String category, String courseId,String userId) {
        coursedb.addCourseCategory(category,courseId,userId);
    }

    
    public void removeCourseCategory(String category, String courseId, String userId) {
        coursedb.removeCourseCategory(category,courseId,userId);
    }
    //******* course content ******

    
    public void addCourseContent(String courseId, Chapter courseChapter, String userId) {
        courseChapter.setCourseId(courseId);
        coursedb.addCourseContent(courseId,courseChapter,userId);
    }

    
    public void deleteCourseContent(String courseId, int lessonNo, String userId) {
        coursedb.deleteCourseContent(courseId,lessonNo,userId);
    }

    //********** course chapter *************

    
    public void changeCourseChapterName(String newChapterName, String courseId, int lessonNo, String userId) {
        coursedb.changeCourseChapterName(newChapterName,courseId,lessonNo,userId);
    }

    
    public void changeCourseChapterContent(String newContent, String courseId, int lessonNo, String userId) {
        coursedb.changeCourseLesson(newContent,courseId,lessonNo,userId);
    }

    //***** Admin Manager Operations **************************************************************************************

    //******* Learner *********************
    
    public boolean removeLearner(String userName) {
        String userId = userdb.getUser(userName).getUserId();
        //unroll courses of user
        ArrayList<String> userEnrolledCourses = coursedb.getEnrolledCourses(userId);
        for(String courseId:userEnrolledCourses) coursedb.unenrollCourse(courseId,userId);
        return userdb.removeLearner(userName);
    }
    
    public boolean changeLearnerPassword(String newPassword, String userName) {
        return userdb.changeLearnerPassword(userName,newPassword);
    }

    //******* Creator ************************
    
    public boolean removeCreator(String userName) {
        String userId = userdb.getUser(userName).getUserId();
        //delete courses of creator
        ArrayList<Course> createdCourses = coursedb.getCreatedCourses(userId);
        for(Course course:createdCourses) deleteCourse(course.getCourseId(),userId);

        return userdb.removeCreator(userName);
    }

    
    public boolean changeCreatorPassword(String newPassword, String userName) {
        return userdb.changeCreatorPassword(userName,newPassword);
    }

    //******* Admin operation ************************
    
    public void removeAdmin(String adminId) {
        userdb.removeAdmin(adminId);
    }

    //******* Z-learn Category operation ************************
    
    public boolean addCategoryToAllCategories(String newCategory) {
        return coursedb.addToAllCategories(newCategory);
    }

    
    public void deleteCategoryFromAllCategories(String category,String userId) {
        coursedb.removeFromAllCategories(category);
        coursedb.removeCoursesBasedOnCategory(category,userId);
    }

    //******* users ************************

    public String getLearnerName(String commenter) {
        return userdb.getLearnerName(commenter);
    }

    public String getCreatorName(String creatorId) {
        return userdb.getCreatorName(creatorId);
    }

    public void addLearner(Learner learner) {
        userdb.addLearner(learner);
    }

    public void addCreator(Creator creator) {
        userdb.addCreator(creator);
    }

    public void addAdmin(Admin admin) {
        userdb.addAdmin(admin);
    }

    //******* course ************************


    public ArrayList<Course> getAllCourses() {
        return new ArrayList<>(coursedb.getAllCourses());
    }

    public Course getCourseDetails(String courseId) {
        return coursedb.getCourse(courseId);
    }

    //******* course Category ************************

    public ArrayList<String> getCategories() {
        return coursedb.getAllCategories();
    }

    public ArrayList<String> getCourseCategories(String courseId) {
        return coursedb.getCourseCategories(courseId);
    }

    public ArrayList<Course> getCoursesBasedOnCategory(String category) {
        return coursedb.getCoursesBasedOnCategory(category);
    }

    //******* course Content ************************

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

    //******* Rating ************************

    public boolean isRatedBy(String userId, String courseId) {
        return coursedb.isRatedBy(userId,courseId);
    }

    //*********** Authenticator Functions ********************************************
    //******** User *******************************************************************

    public AuthStatus userAuthentication(String userName, String password){
        User user = userdb.getUser(userName);
        boolean isUserFound = false;
        if(user != null){
            isUserFound = true;
            if(user.isCorrectPassword(password)){
                return AuthStatus.LOGIN_SUCCESS;
            }
        }
        if(isUserFound) return AuthStatus.PASSWORD_MISMATCH;
        return AuthStatus.USERNAME_NOT_FOUND;
    }


    public User getUser(String userName) {
        return userdb.getUser(userName);
    }

    public boolean isUserNameAvailable(String userName) {
        return !userdb.isUserExist(userName);
    }


    //******** Learner *******************************************************************


    public void addLearner(String userName, String password, String firstName) {
        String userId = IdGenerator.getNewLearnerId();
        userdb.addLearner(new Learner(userId,userName,password,firstName));
    }

    //******* Creator ********************************************************************

    public void addCreator(String userName, String password, String firstName) {
        String userId = IdGenerator.getNewCreatorId();
        userdb.addCreator(new Creator(userId,userName,password,firstName));
    }

    //******* Admin ********************************************************************

}
