package Database;


import Core.Users.*;

import java.util.ArrayList;

class UserDatabase implements UserDBOperations{
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
    //*** User *************************************************************************

    public User getUser(String userName) {
        User user = null;
        for(User usr:this.users){
            if(usr.getUserName().equals(userName)){
                user = copyUser(usr);
                break;
            }
        }
        return user;
    }

    private User copyUser(User user){
        User usr = null;
        if(user.getRole().equals(ROLE.CREATOR)) usr = new Creator((Creator) user);
        else if(user.getRole().equals(ROLE.LEARNER)) usr = new Learner((Learner) user);
        else if(user.getRole().equals(ROLE.ADMIN)) usr = new Admin((Admin) user);
        return usr;
    }

    public boolean isUserExist(String userName) {
        User user = getUser(userName);
        return user != null;
    }

    //*** Learner *************************************************************************
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
        Learner learner = (Learner) getUser(userName);
        return learner != null && this.users.remove(learner);
    }
    public boolean changeLearnerPassword(String userName,String newPassword){
        Learner learner = (Learner) getUser(userName);
        if(learner!=null){
            int learnerIndex = getLearnerIndex(learner.getUserId());
            this.users.get(learnerIndex).changePassword(newPassword);
            return true;
        }
        return false;
    }

    //*** Creator *************************************************************************

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
        Creator creator = (Creator) getUser(userName);
        return creator != null && this.users.remove(creator);
    }

    public boolean changeCreatorPassword(String userName,String newPassword){
        Creator creator = (Creator) getUser(userName);
        if(creator!=null){
            int creatorIndex = getCreatorIndex(creator.getUserId());
            this.users.get(creatorIndex).changePassword(newPassword);
            return true;
        }
        return false;
    }


    //*** Admin *************************************************************************
    public void addAdmin(Admin admin) {
        this.users.add(admin);
    }

    public void removeAdmin(String adminId){
        Admin admin = (Admin) getUser(adminId);
        if(admin!=null){
            this.users.remove(admin);
        }
    }

}
