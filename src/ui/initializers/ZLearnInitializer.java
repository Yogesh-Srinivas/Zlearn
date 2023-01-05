package ui.initializers;

import core.course.Chapter;
import core.users.Admin;
import core.users.Creator;
import core.users.Learner;
import database.DatabaseManager;
import managers.*;

import java.util.ArrayList;

public class ZLearnInitializer {
    public static void initiate(){
        DatabaseManager dbManager = new DatabaseManager();
        Learner learner1 = new Learner(IdGenerator.getNewLearnerId(), "Yogi", "123", "Yogesh");
        Learner learner2 = new Learner(IdGenerator.getNewLearnerId(), "Logan", "123", "Loganathan");
        Learner learner3 = new Learner(IdGenerator.getNewLearnerId(), "Avinash", "123", "Avinash");


        Creator creator1 = new Creator(IdGenerator.getNewCreatorId(), "Sivanesh", "123", "Shivaneshwaran");
        Creator creator2 = new Creator(IdGenerator.getNewCreatorId(),"Sathya","123","Sathyanarayanan");

        Admin admin1 = new Admin(IdGenerator.getNewAdminId(), "123","Rahul");

        dbManager.addLearner(learner1);
        dbManager.addLearner(learner2);
        dbManager.addLearner(learner3);
        dbManager.addCreator(creator1);
        dbManager.addCreator(creator2);
        dbManager.addAdmin(admin1);
        learner1.enrollCourse("Course_101");
        learner1.enrollCourse("ZCourse_101");

        //add All Categories
        admin1.addCategoryToAllCategories("Finance");
        admin1.addCategoryToAllCategories("Self Help");
        admin1.addCategoryToAllCategories("Business");
        admin1.addCategoryToAllCategories("Coding");
        admin1.addCategoryToAllCategories("General");

        //course Category

        ArrayList<String> category = new ArrayList<String>(){
            {
                add("Finance");
                add("Self Help");
                add("Business");
            }
        };

        //course Content
        ArrayList<Chapter> content1 = new ArrayList<Chapter>(){
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

        ArrayList<Chapter> content2 = new ArrayList<Chapter>(){
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

        ArrayList<Chapter> content3 = new ArrayList<Chapter>(){
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

        //add Courses
        creator2.addNewCourse("The Minions",category,content1,120);
        creator2.addNewCourse("The Business School", category,content2,350);
        admin1.addNewCourse("The Minions 2", category,content3, 0);

        //add course1 comments
        learner1.addComment("Nice course","Course_101");
        learner2.addComment("Awesome course","Course_101");
        learner3.addComment("Need More Improvement","Course_101");
        learner1.addComment("Add more content like this.","Course_101");
        learner2.addComment("Add more content like this.","Course_101");
        learner3.addComment("Add more content.","Course_101");

        //add course2 comments
        learner3.addComment("Need More Improvement","Course_102");
        learner1.addComment("Add more content like this.","Course_102");
        learner2.addComment("Awesome course","Course_102");
        learner3.addComment("Nice course","Course_102");

        //add course3 comments
        learner1.addComment("Need More Improvement","ZCourse_101");
        learner2.addComment("Awesome course","ZCourse_101");
        learner3.addComment("Add more content like this.","ZCourse_101");
        learner2.addComment("Nice course","ZCourse_101");

    }
}
