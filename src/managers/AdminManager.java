package managers;


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
    boolean addCategoryToAllCategories(String newCategory);

    void deleteCategoryFromAllCategories(String category,String userId);

}
