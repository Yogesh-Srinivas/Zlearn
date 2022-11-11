package Core.Users;

import Managers.AdminManager;
import Managers.UserManager;

public class Admin{
    private final String adminId;
    private String adminPassword;
    private final AdminManager adminManager = new UserManager();

    public Admin(String adminId, String adminPassword) {
        this.adminId = adminId;
        this.adminPassword = adminPassword;
    }

    public String getAdminId() {
        return adminId;
    }

    public boolean isCorrectPassword(String adminPassword) {
        return this.adminPassword.equals(adminPassword);
    }
    public void removeLearner(String userName){
        adminManager.removeLearner(userName);
    }
    public void removeCreator(String userName){
        adminManager.removeCreator(userName);
    }
    public void removeAdmin(String adminId){
        adminManager.removeAdmin(adminId);
    }

    public void changeLearnerPassword(String newPassword,String userName){
        adminManager.changeLearnerPassword(newPassword,userName);
    }

    public void changeCreatorPassword(String newPassword,String userName){
        adminManager.changeCreatorPassword(newPassword,userName);
    }

    public void addCategoryToAllCategories(String newCategory) {
        adminManager.addCategoryToAllCategories(newCategory);
    }

    public void deleteCategoryFromAllCategories(String category) {
        adminManager.deleteCategoryFromAllCategories(category);
    }
}
