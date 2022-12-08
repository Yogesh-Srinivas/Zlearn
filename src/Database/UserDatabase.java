package Database;


import Core.Users.*;

import java.util.ArrayList;

public class UserDatabase implements UserDBOperations{
    private final ArrayList<User> users = new ArrayList<>();

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
        for(User usr:this.users){
            if(usr.getRole().equals(ROLE.LEARNER) && usr.getUserName().equals(userName)){
                learner = (Learner) usr;
                break;
            }
        }
        return learner;
    }
    private int getLearnerIndex(String userId){
        int learnerIndex = -1;
        for(User usr:this.users){
            if(usr.getUserId().equals(userId) && usr.getRole().equals(ROLE.LEARNER)){
                learnerIndex = this.users.indexOf(usr);
            }
        }
        return learnerIndex;
    }

    public String getLearnerName(String commentor) {
        int learnerIndex = getLearnerIndex(commentor);
        if(learnerIndex==-1) return "NULL";
        return this.users.get(learnerIndex).getFirstName();
    }
    public void addLearner(Learner learner) {
        this.users.add(learner);
    }

    public boolean removeLearner(String userName){
        Learner learner = getLearner(userName);
        if(learner!=null && this.users.remove(learner)) return true;
        return false;
    }

    public boolean changeLearnerPassword(String userName,String newPassword){
        Learner learner = getLearner(userName);
        if(learner!=null){
            int learnerIndex = getLearnerIndex(learner.getUserId());
            this.users.get(learnerIndex).changePassword(newPassword);
            return true;
        }
        return false;
    }
    public boolean isLearnerExist(String userName) {
        Learner learner = getLearner(userName);
        if(learner==null) return false;
        return true;
    }


    //*** Creator *************************************************************************
    public Creator getCreator(String userName) {
        Creator creator = null;
        for(User usr:this.users){
            if(usr.getRole().equals(ROLE.CREATOR) && usr.getUserName().equals(userName) ){
                creator = (Creator) usr;
                break;
            }
        }
        return creator;
    }

    private int getCreatorIndex(String userId){
        int creatorIndex = -1;
        for(User usr:this.users){
            if(usr.getUserId().equals(userId) && usr.getRole().equals(ROLE.CREATOR)){
                creatorIndex = this.users.indexOf(usr);
            }
        }
        return creatorIndex;
    }

    public String getCreatorName(String userId){
        if(userId.contains("Adm")) return "ZLearn";
        int creatorIndex = getCreatorIndex(userId);
        if(creatorIndex==-1) return "NULL";
        return this.users.get(creatorIndex).getFirstName();
    }

    public void addCreator(Creator creator) {
        this.users.add(creator);
    }

    public boolean removeCreator(String userName){
        Creator creator = getCreator(userName);
        if(creator!=null && this.users.remove(creator)) return true;
        return false;
    }

    public boolean changeCreatorPassword(String userName,String newPassword){
        Creator creator = getCreator(userName);
        if(creator!=null){
            int creatorIndex = getCreatorIndex(creator.getUserId());
            this.users.get(creatorIndex).changePassword(newPassword);
            return true;
        }
        return false;
    }
    public boolean isCreatorExist(String userName) {
        Creator creator = getCreator(userName);
        if(creator==null) return false;
        return true;
    }

    //*** Admin *************************************************************************
    public Admin getAdmin(String adminId) {
        Admin admin = null;
        for(User usr:this.users){
            if(usr.getRole().equals(ROLE.ADMIN) && usr.getUserId().equals(adminId)){
                admin = (Admin) usr;
                break;
            }
        }
        return admin;
    }

    public void addAdmin(Admin admin) {
        this.users.add(admin);
    }

    public void removeAdmin(String adminId){
        Admin admin = getAdmin(adminId);
        if(admin!=null){
            this.users.remove(admin);
        }
    }




}
