package Managers;

import Core.Course.Chapter;

import java.util.ArrayList;

public interface AdminManager {
    boolean removeLearner(String userName);

    boolean removeCreator(String userName);

    void removeAdmin(String adminId);

    boolean changeLearnerPassword(String newPassword, String userName);

    boolean changeCreatorPassword(String newPassword, String userName);

    void addCategoryToAllCategories(String newCategory);

    void deleteCategoryFromAllCategories(String category);

    void addNewZCourse(String courseName, ArrayList<String> selectedCategories, ArrayList<Chapter> courseContent, int coursePrice, String adminId);

}
