package Core.Users;

public abstract class User {
    private  String userId;
    private  String userName;
    private String password;
    private String firstName;
    private  ROLE role;

    private User() {}
     User(String userId,String userName,String password,String firstName,ROLE role){
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.role = role;
    }
    //need to add login manager to change password
    public void changePassword(String newPassword){
        this.password = newPassword;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPassword(){ return password;}

    public boolean isCorrectPassword(String password) {
        return this.password.equals(password);
    }

    public ROLE getRole() {
        return role;
    }

}
