package managers;

import core.course.Chapter;
import core.course.Comment;
import core.course.Course;

import java.util.ArrayList;

public interface CreatorManager {


    //******* course ******
    void addNewCourse(String courseName, ArrayList<String> courseCategories, ArrayList<Chapter> courseContent, int coursePrice, String userId);

    boolean deleteCourse(String courseId, String userId);

    ArrayList<Course> getCreatedCourse(String userId);

    //******* course comment ******

    ArrayList<Comment> getCourseComments(String courseId);

    //******** course name ************
    void changeCourseName(String newCourseName,String courseId,String userId);

    //******* course price ******
    void changeCoursePrice(int newPrice, String courseId, String userId);

    //******* course category ******
    void addCourseCategory(String category, String courseId,String userId);

    void removeCourseCategory(String category, String courseId, String userId);

    //******* course content ******
    void addCourseContent(String courseId, Chapter courseChapter, String userId);

    void deleteCourseContent(String courseId, int lessonNo, String userId);

    //********** course chapter *************

    void changeCourseChapterName(String newChapterName, String courseId, int lessonNo, String userId);

    void changeCourseChapterContent(String newContent, String courseId, int lessonNo, String userId);
}
