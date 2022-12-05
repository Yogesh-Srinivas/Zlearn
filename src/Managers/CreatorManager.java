package Managers;

import Core.Course.Chapter;
import Core.Course.Comment;
import Core.Course.Course;

import java.util.ArrayList;

public interface CreatorManager {

    boolean deleteCourse(String courseId, String userId);

    void changeCourseName(String newCourseName,String courseId,String userId);

    void changeCoursePrice(int newPrice, String courseId, String userId);

    void addCourseCategory(String category, String courseId,String userId);

    void removeCourseCategory(String category, String courseId, String userId);

    ArrayList<Course> getCreatedCourse(String userId);

    ArrayList<Comment> getCourseComments(String courseId,String userId);

    void addNewCourse(String courseName, ArrayList<String> courseCategories, ArrayList<Chapter> courseContent, int coursePrice, String userId);

    void addCourseContent(String courseId, Chapter courseChapter, String userId);

    void deleteCourseContent(String courseId, int contentIndex, String userId);

    void changeCourseChapterName(String newChapterName, String courseId, int contentIndex, String userId);

    void changeCourseChapterContent(String newContent, String courseId, int contentIndex, String userId);
}
