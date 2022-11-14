package Database;


import Core.Users.Admin;
import Core.Users.Creator;
import Core.Users.Learner;
import UI.AuthStatus;

import java.util.ArrayList;

public class UserDatabase implements UserDBOperations{
    private final ArrayList<Learner> learners = new ArrayList<>();
    private final ArrayList<Creator> creators = new ArrayList<>();
    private final ArrayList<Admin> admins = new ArrayList<>();

    //********* Constructor *******************************************************************
    private UserDatabase(){}
    private static UserDatabase instance = null;
    public static UserDatabase getInstance(){
        if(instance == null){
            instance= new UserDatabase();
        }
        return instance;
    }
    //*********** Getters and Setters ************************************************************

    //*** Learner *************************************************************************
    public Learner getLearner(String userName) {
        Learner learner = null;
        for(Learner l:this.learners){
            if(l.getUserName().equals(userName)){
                learner = l;
                break;
            }
        }
        return learner;
    }

    private int getLearnerIndex(String userId){
        int learnerIndex = -1;
        for(Learner l : this.learners){
            if(l.getUserId().equals(userId)){
                learnerIndex = this.learners.indexOf(l);
            }
        }
        return learnerIndex;
    }

    public String getLearnerName(String commentor) {
        int learnerIndex = getLearnerIndex(commentor);
        return this.learners.get(learnerIndex).getFirstName();
    }

    public double getLearnerCurrentProgress(String courseId,String userId){
        int learnerIndex = getLearnerIndex(userId);
        return this.learners.get(learnerIndex).getCourseProgress(courseId);
    }

    public void addLearner(Learner learner) {
        this.learners.add(learner);
    }

    public void removeLearner(String userName){
        for(Learner l:this.learners){
            if(l.getUserName().equals(userName)){
                this.learners.remove(l);
                break;
            }
        }
    }

    public void changeLearnerPassword(String userName,String newPassword){
        for(Learner l:this.learners){
            if(l.getUserName().equals(userName)){
                this.learners.get(this.learners.indexOf(l)).changePassword(newPassword);
                break;
            }
        }
    }

    public void addCourseToLearner(String userId,String courseId){
        int learnerIndex = getLearnerIndex(userId);
        this.learners.get(learnerIndex).addEnrolledCourse(courseId);
    }

    public AuthStatus learnerAuthentication(String userName, String password) {
        boolean isUserFound = false;
        for(Learner learner : this.learners){
            if(learner.getUserName().equals(userName)){
                isUserFound = true;
                if(learner.isCorrectPassword(password)){
                    return AuthStatus.LOGIN_SUCCESS;
                }
            }
        }
        if(isUserFound) return AuthStatus.PASSWORD_MISMATCH;
        return AuthStatus.USERNAME_NOT_FOUND;
    }

    public boolean isLearnerExist(String userName) {
        boolean isUserExist = false;
        for(Learner learner:this.learners){
            if(learner.getUserName().equals(userName)){
                isUserExist = true;
                break;
            }
        }
        return isUserExist;
    }

    public void unenrollCourse(String courseId, String userId) {
        int learnerIndex = getLearnerIndex(userId);
        this.learners.get(learnerIndex).removeCourse(courseId);
    }

    public void updateUserProgress(String courseId, String userId, double currentProgress){
        int learnerIndex = getLearnerIndex(userId);
        this.learners.get(learnerIndex).updateProgress(courseId,currentProgress);
    }

    public ArrayList<String> getCourseLearners(String courseId) {
        ArrayList<String> courseLearners = new ArrayList<>();
        for(Learner learner:this.learners){
            if(learner.isEnrolled(courseId)){
                courseLearners.add(learner.getUserId());
            }
        }
        return courseLearners;
    }

    //*** Creator *************************************************************************
    public Creator getCreator(String userName) {
        Creator creator = null;
        for(Creator c:this.creators){
            if(c.getUserName().equals(userName)){
                creator = c;
                break;
            }
        }
        return creator;
    }

    private int getCreatorIndex(String userId){
        int creatorIndex = -1;
        for(Creator c : this.creators){
            if(c.getUserId().equals(userId)){
                creatorIndex = this.creators.indexOf(c);
            }
        }
        return creatorIndex;
    }

    public String getCreatorName(String userId){
        int creatorIndex = getCreatorIndex(userId);
        if(userId.contains("Adm")) return "ZLearn";
        return this.creators.get(creatorIndex).getFirstName();
    }

    public void addCreator(Creator creator) {
        this.creators.add(creator);
    }

    public void removeCreator(String userName){
        for(Creator c:this.creators){
            if(c.getUserName().equals(userName)){
                this.creators.remove(c);
                break;
            }
        }
    }

    public void changeCreatorPassword(String userName,String newPassword){
        for(Creator c:this.creators){
            if(c.getUserName().equals(userName)){
                this.creators.get(this.creators.indexOf(c)).changePassword(newPassword);
                break;
            }
        }
    }

    public AuthStatus creatorAuthentication(String userName, String password) {
        boolean isUserFound = false;
        for(Creator creator : this.creators){
            if(creator.getUserName().equals(userName)){
                isUserFound = true;
                if(creator.isCorrectPassword(password)){
                    return AuthStatus.LOGIN_SUCCESS;
                }
            }
        }
        if(isUserFound) return AuthStatus.PASSWORD_MISMATCH;
        return AuthStatus.USERNAME_NOT_FOUND;
    }

    public boolean isCreatorExist(String userName) {
        boolean isUserExist = false;
        for(Creator creator:this.creators){
            if(creator.getUserName().equals(userName)){
                isUserExist = true;
                break;
            }
        }
        return isUserExist;
    }

    //*** Admin *************************************************************************
    public Admin getAdmin(String adminId) {
        Admin admin = null;
        for(Admin a:this.admins){
            if(a.getAdminId().equals(adminId)){
                admin = a;
                break;
            }
        }
        return admin;
    }

    public void addAdmin(Admin admin) {
        this.admins.add(admin);
    }

    public void removeAdmin(String adminId){
        for(Admin a:this.admins){
            if(a.getAdminId().equals(adminId)){
                this.admins.remove(a);
                break;
            }
        }
    }

    public AuthStatus adminAuthentication(String adminId, String adminPassword) {
        boolean isUserFound = false;
        for(Admin admin : this.admins){
            if(admin.getAdminId().equals(adminId)){
                isUserFound = true;
                if(admin.isCorrectPassword(adminPassword)){
                    return AuthStatus.LOGIN_SUCCESS;
                }
            }
        }
        if(isUserFound) return AuthStatus.PASSWORD_MISMATCH;
        return AuthStatus.ID_NOT_FOUND;
    }

}
