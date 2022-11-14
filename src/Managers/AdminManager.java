package Managers;

import Core.Course.Chapter;

import java.util.ArrayList;

public interface AdminManager {
    void removeLearner(String userName);

    void removeCreator(String userName);

    void removeAdmin(String adminId);

    void changeLearnerPassword(String newPassword, String userName);

    void changeCreatorPassword(String newPassword, String userName);

    void addCategoryToAllCategories(String newCategory);

    void deleteCategoryFromAllCategories(String category);

    void removeCourse(String courseId,String adminId);

    void addNewZCourse(String courseName, ArrayList<String> selectedCategories, ArrayList<Chapter> courseContent, int coursePrice, String adminId);

    void addCourseContent(String courseId, Chapter newChapter,String adminId);

    void deleteCourseContent(String courseId, int contentIndex, String adminId);

    void changeCourseChapterName(String newChapterName, String courseId, int contentIndex,String adminId);

    void changeCourseChapterContent(String newContent, String courseId, int contentIndex, String adminId);

    void changeCoursePrice(int coursePrice, String courseId, String adminId);

    void removeCourseCategory(String categoryToRemove, String courseId, String adminId);

    void addCourseCategory(String catergoryToadd, String courseId, String adminId);

    void changeCourseName(String newCourseName, String courseId, String adminId);
}
