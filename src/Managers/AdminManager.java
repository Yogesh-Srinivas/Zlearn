package Managers;


public interface AdminManager {

    //******* Learner ******
    boolean removeLearner(String userName);
    boolean changeLearnerPassword(String newPassword, String userName);

    //******* Creator ******
    boolean removeCreator(String userName);

    boolean changeCreatorPassword(String newPassword, String userName);

    //******* Admin ******

    void removeAdmin(String adminId);

    //******* All Categories ******
    void addCategoryToAllCategories(String newCategory);

    void deleteCategoryFromAllCategories(String category);

}
