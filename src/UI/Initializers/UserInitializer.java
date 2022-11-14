package UI.Initializers;

import Core.Users.Admin;
import Core.Users.Creator;
import Core.Users.Learner;
import Database.UserDatabase;

public class UserInitializer {
    public static void initiateUsers(){
        UserDatabase userdb = UserDatabase.getInstance();
        Learner learner1 = new Learner("Lrn_1100", "Yogi", "123", "Yogesh");
        Learner learner2 = new Learner("Lrn_1101", "Logan", "123", "Loganathan");
        Learner learner3 = new Learner("Lrn_1102", "Avinash", "123", "Avinash");

        Creator creator1 = new Creator("Ctr_1104", "Sivanesh", "123", "Shivaneshwaran");
        Creator creator2 = new Creator("Ctr_1105","Sathya","123","Sathyanarayanan");

        Admin admin1 = new Admin("Adm_123", "123");

        userdb.addLearner(learner1);
        userdb.addLearner(learner2);
        userdb.addLearner(learner3);
        userdb.addCreator(creator1);
        userdb.addCreator(creator2);
        userdb.addAdmin(admin1);
        learner1.enrollNewCourse("cs001");
        learner1.enrollNewCourse("ZCourse_001");
    }
}
