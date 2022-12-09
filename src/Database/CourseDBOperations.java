package Database;

import Core.Course.Chapter;
import Core.Course.Comment;
import Core.Course.Course;
import Core.Course.CourseProgress;

import java.util.ArrayList;

 public interface CourseDBOperations {
     //******* courses ******
     Course getCourse(String courseId);
     ArrayList<Course> getCreatedCourses(String userId);
     ArrayList<Course> getAllCourses();

     //******* edit courses ******
     void addCourse(Course course,ArrayList<Chapter> content);
     boolean deleteCourse(String courseId,String userId);
     void changeCourseName(String newCourseName,String courseId, String userId);
     void changeCoursePrice(int newPrice, String courseId, String userId);
     void changeCourseChapterName(String newChapterName, String courseId, int lessonNo, String userId);

     //******* all categories ******
     ArrayList<String> getAllCategories();
     void addToAllCategories(String category);
     void removeFromAllCategories(String category);

     //******* comments ******
     ArrayList<Comment> getCourseComments(String courseId);
     void addComment(String comment, String courseId, String userId);
     void deleteCourseComments(String courseId, String userId);

     //******* course content ******
     ArrayList<Chapter> getCourseContent(String courseId);
     Chapter getChapter(String courseId,int lessonNo);
     ArrayList<String> getCourseLearnings(String courseId);
     void addCourseContent(String courseId, Chapter courseChapter, String userId);
     void deleteCourseContent(String courseId, int lessonNo, String userId);
     void deleteCourseContent(String courseId, String userId);
     //******* rated by ******

     void rateCourse(String courseId, int rating, String userId);
     void deleteCourseRatedBy(String courseId, String userId);
     boolean isRatedBy(String userId,String courseId);

     //******* categories ******
     ArrayList<Course> getCoursesBasedOnCategory(String category);
     ArrayList<String> getCourseCategories(String courseId);
     void addCourseCategory(String category, String courseId, String userId);
     void removeCourseCategory(String category, String courseId, String userId);

     void deleteCourseCategory(String courseId, String userId);
     //******* course progress / Enrollment ******

     ArrayList<String> getEnrolledCourses(String userId);

     ArrayList<String> getCourseLearners(String courseId);

     void changeCourseLesson(String newLesson, String courseId, int lessonNo, String userId);
     double getCourseProgress(String courseId,String userId);
     int getCourseChapterCount(String courseId);
     boolean isEnrolled(String courseId,String userId);
     void enrollCourse(CourseProgress courseProgress);
     void updateProgress(String courseId,String userId, double currentProgress);
     void unenrollCourse(String courseId,String userId);
 }
