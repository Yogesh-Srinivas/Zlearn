package Managers;

public interface AdminManager {
    void removeLearner(String userName);

    void removeCreator(String userName);

    void removeAdmin(String adminId);

    void changeLearnerPassword(String newPassword, String userName);

    void changeCreatorPassword(String newPassword, String userName);

    void addCategoryToAllCategories(String newCategory);

    void deleteCategoryFromAllCategories(String category);
}
