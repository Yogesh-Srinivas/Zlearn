package Core.Users;

import Managers.AdminManager;
import Managers.UserManager;
import UI.AdminOperations;

public class Admin extends Creator{
    private final AdminManager adminManager = new UserManager();
    private final ROLE role = ROLE.ADMIN;

    //******* Constructor *******************************

    public Admin(String userId,String password,String firstName){
        super(userId,userId,password,firstName);
    }

    //******* Getters and Setters ********************************************************************

    public ROLE getRole() {
        return role;
    }
    @Override
    public void openDashboard() {
        new AdminOperations(this).adminDashBoard();
    }

    //******* Learner operation  *********************

    public boolean removeLearner(String userName){
        return adminManager.removeLearner(userName);
    }
    public boolean changeLearnerPassword(String newPassword,String userName){
        return adminManager.changeLearnerPassword(newPassword,userName);
    }

    //******* Creator operation ************************

    public boolean removeCreator(String userName){
        return adminManager.removeCreator(userName);
    }
    public boolean changeCreatorPassword(String newPassword,String userName){
        return adminManager.changeCreatorPassword(newPassword,userName);
    }

    //******* Admin operation ************************

    public void removeAdmin(String adminId){
        adminManager.removeAdmin(adminId);
    }

    //******* Zlearn Category operation ************************

    public void addCategoryToAllCategories(String newCategory) {
        adminManager.addCategoryToAllCategories(newCategory);
    }

    public void deleteCategoryFromAllCategories(String category) {
        adminManager.deleteCategoryFromAllCategories(category);
    }

}
