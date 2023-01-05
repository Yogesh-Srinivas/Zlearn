package core.users;

import managers.AdminManager;
import managers.UserManager;
import ui.AdminOperations;

public class Admin extends Creator{
    private final AdminManager adminManager = new UserManager();
    private final ROLE role = ROLE.ADMIN;

    //******* Constructor *******************************

    public Admin(String userId,String password,String firstName){
        super(userId,userId,password,firstName);
    }

    //Copy Constructor

    public Admin(Admin admin){
        super(admin);
    }

    @Override
    public User copy(){
        return new Admin(this);
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

    public boolean addCategoryToAllCategories(String newCategory) {
        return adminManager.addCategoryToAllCategories(newCategory);
    }

    public void deleteCategoryFromAllCategories(String category) {
        adminManager.deleteCategoryFromAllCategories(category);
    }

}
