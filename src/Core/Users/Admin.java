package Core.Users;

import Managers.AdminManager;
import Managers.UserManager;

public class Admin{
    private String adminId;
    private String adminPassword;
    private AdminManager adminManager = new UserManager();

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
    public void removeLearner(String userId){
        adminManager.removeLearner(userId);
    }
    public void removeCreator(String userId){
        adminManager.removeCreator(userId);
    }
    public void removeAdmin(String adminId){
        adminManager.removeAdmin(adminId);
    }

    public void changeLearnerPassword(String newPassword,String userId){
        adminManager.changeLearnerPassword(newPassword,userId);
    }

    public void changeCreatorPassword(String newPassword,String userId){
        adminManager.changeCreatorPassword(newPassword,userId);
    }
}
