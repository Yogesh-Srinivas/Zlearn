package UI.Initializers;

import Core.Course.Chapter;
import Database.CourseDatabase;
import Managers.CreatorManager;
import Managers.LearnerManager;
import Managers.UserManager;

import java.util.ArrayList;

public class CourseInitializer {
    public static void initiateCourses(){
        CourseDatabase coursedb = CourseDatabase.getInstance();
        CreatorManager creatorManager = new UserManager();
        LearnerManager learnerManager = new UserManager();
        ArrayList<String> category = new ArrayList<String>(){
            {
                add("Finance");
                add("Self Help");
                add("Business");
            }
        };
        coursedb.addToAllCategories("Finance");
        coursedb.addToAllCategories("Self Help");
        coursedb.addToAllCategories("Business");
        coursedb.addToAllCategories("Coding");

        ArrayList<Chapter> content = new ArrayList<Chapter>(){
            {
                add(new Chapter("Lesson 1", "Yellow is new black",null,1));
                add(new Chapter("Lesson 2","This is minions, they have yellow skin color with round eyes!",null,2));
                add(new Chapter("Lesson 3","Minions appears in despicable me movie and owns its franchises",null,3));
                add(new Chapter("Lesson 4","Minions appears in despicable me movie and owns its franchises",null,4));
                add(new Chapter("Lesson 5","Minions appears in despicable me movie and owns its franchises",null,5));
                add(new Chapter("Lesson 6","Minions appears in despicable me movie and owns its franchises",null,6));
                add(new Chapter("Lesson 7","Minions appears in despicable me movie and owns its franchises",null,7));
                add(new Chapter("Lesson 8","Minions appears in despicable me movie and owns its franchises",null,8));

            }
        };
        creatorManager.addNewCourse("The Minions",category,content,120,"Ctr_1002");
        learnerManager.addComment("Nice Course","cs001","Lrn_1001");
        learnerManager.addComment("Awesome Course","cs001","Lrn_1002");
        learnerManager.addComment("Need More Improvement","cs001","Lrn_1003");
        learnerManager.addComment("Add more content like this.","cs001","Lrn_1001");
        learnerManager.addComment("Add more content like this.","cs001","Lrn_1102");
        learnerManager.addComment("Add more content.","cs001","Lrn_1003");

        creatorManager.addNewCourse("The Minions 2", category,content, 0,"Adm_1");
        learnerManager.addComment("Need More Improvement","ZCourse_001","Lrn_1001");
        learnerManager.addComment("Awesome Course","ZCourse_001","Lrn_1002");
        learnerManager.addComment("Add more content like this.","ZCourse_001","Lrn_1003");
        learnerManager.addComment("Nice Course","ZCourse_001","Lrn_1002");

        creatorManager.addNewCourse("The Business School", category,content,350,"Ctr_1002");
        learnerManager.addComment("Need More Improvement","bs001","Lrn_1003");
        learnerManager.addComment("Add more content like this.","bs001","Lrn_1001");
        learnerManager.addComment("Awesome Course","bs001","Lrn_1002");
        learnerManager.addComment("Nice Course","bs001","Lrn_1003");

    }
}
