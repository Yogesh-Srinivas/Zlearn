package UI.Initializers;

import Core.Users.Admin;
import Core.Users.Creator;
import Core.Users.Learner;
import Core.Users.ROLE;
import Database.UserDatabase;

public class UserInitializer {
    public static void initiateUsers(){
        UserDatabase userdb = UserDatabase.getInstance();
        Learner learner1 = new Learner("1001", "Yogi", "123", "Yogesh", ROLE.LEARNER);
        Learner learner2 = new Learner("1002", "Logan", "123", "Loganathan", ROLE.LEARNER);
        Learner learner3 = new Learner("1003", "Avinash", "123", "Avinash", ROLE.LEARNER);

        Creator creator1 = new Creator("1004", "Sivanesh", "123", "Shivaneshwaran", ROLE.CREATOR);
        Creator creator2 = new Creator("1005","Sathya","123","Sathyanarayanan",ROLE.CREATOR);

        Admin admin1 = new Admin("ad123", "123");

        userdb.addLearner(learner1);
        userdb.addLearner(learner2);
        userdb.addLearner(learner3);
        userdb.addCreator(creator1);
        userdb.addCreator(creator2);
        userdb.addAdmin(admin1);
        learner1.enrollNewCourse("cs001");
        learner1.enrollNewCourse("cs002");
    }
}
