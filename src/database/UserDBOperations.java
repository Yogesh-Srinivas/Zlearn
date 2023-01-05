package database;

import core.users.Admin;
import core.users.Creator;
import core.users.Learner;
import core.users.User;

interface UserDBOperations {

    //*** User *************************************************************************
     boolean isUserExist(String userName);
     User getUser(String userName);
    //*** Learner *************************************************************************
     String getLearnerName(String commenter);
     void addLearner(Learner learner);
     boolean removeLearner(String userName);
     boolean changeLearnerPassword(String userName,String newPassword);
    //*** Creator *************************************************************************
     String getCreatorName(String userId);
     void addCreator(Creator creator);
     boolean removeCreator(String userName);
     boolean changeCreatorPassword(String userName,String newPassword);
    //*** Admin *************************************************************************
     void addAdmin(Admin admin);
     void removeAdmin(String adminId);

}
