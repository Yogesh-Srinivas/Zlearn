package Managers;


public interface AdminManager {
    boolean removeLearner(String userName);

    boolean removeCreator(String userName);

    void removeAdmin(String adminId);

    boolean changeLearnerPassword(String newPassword, String userName);

    boolean changeCreatorPassword(String newPassword, String userName);

    void addCategoryToAllCategories(String newCategory);

    void deleteCategoryFromAllCategories(String category);

}
