package Core.Users;

public class Admin{
    private String adminId;
    private String adminPassword;

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
}
