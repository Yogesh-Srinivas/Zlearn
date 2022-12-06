package Database;

import Core.Course.Chapter;
import Core.Course.Comment;
import Core.Course.Course;
import Core.Course.CourseCategory;

import java.util.ArrayList;

 public interface CourseDBOperations {
     Course getCourse(String courseId);
     void addCourse(Course course,ArrayList<Chapter> content);
     boolean deleteCourse(String courseId,String userId);
     ArrayList<String> getAllCategories();
     void addToAllCategories(String category);
     void removeFromAllCategories(String category);
     ArrayList<Course> getCoursesBasedOnCategory(String category);
     void addCourseCategory(String category, String courseId, String userId);
     void removeCourseCategory(String category, String courseId, String userId);
     ArrayList<Comment> getCourseComments(String courseId, String userId);
     void addComment(String comment, String courseId, String userId);
     ArrayList<Course> getCreatedCourses(String userId);
     ArrayList<Course> getAllCourses();
     void rateCourse(String courseId, int rating, String userId);
     void changeCourseName(String newCourseName,String courseId, String userId);
     void changeCoursePrice(int newPrice, String courseId, String userId);
     void addCourseContent(String courseId, Chapter courseChapter, String userId);
     void deleteCourseContent(String courseId, int contentIndex, String userId);
     void changeCourseChapterName(String newChapterName, String courseId, int chapterIndex, String userId);
     void changeCourseLesson(String newLesson, String courseId, int chapterIndex, String userId);
     ArrayList<Chapter> getCourseContent(String courseId);
     Chapter getChapter(String courseId,int chapterIndex);
     ArrayList<String> getCourseLearnings(String courseId);
     ArrayList<String> getCourseCategories(String courseId);
     int getCourseChapterCount(String courseId);
     boolean isRatedBy(String userId,String courseId);

}
