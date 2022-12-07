package Core.Users;

public abstract class User {
    private final String userId;
    private final String userName;
    private String password;
    private final String firstName;
    //******* Constructor ***************************************************************************

     User(String userId,String userName,String password,String firstName){
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
    }
    //******* Getters and Setters ********************************************************************

    public String getUserId() {
        return userId;
    }

    public boolean isCorrectPassword(String password) {
        return this.password.equals(password);
    }

    public void changePassword(String newPassword){
        this.password = newPassword;
    }

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }


    public abstract void openDashboard();
}
