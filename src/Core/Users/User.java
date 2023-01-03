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

    //Copy Constructor
    User(User user){
        this.userId = user.userId;
        this.userName = user.userName;
        this.password = user.password;
        this.firstName = user.firstName;
    }
    //******* Getters and Setters ********************************************************************

    public abstract void openDashboard();
    public abstract ROLE getRole();

    //******* user id ******

    public String getUserId() {
        return userId;
    }

    //******* user name ******

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    //******* password ******

    public boolean isCorrectPassword(String password) {
        return this.password.equals(password);
    }

    public void changePassword(String newPassword){
        this.password = newPassword;
    }

}
