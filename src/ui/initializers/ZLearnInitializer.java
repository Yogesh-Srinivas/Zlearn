package ui.initializers;

import core.course.Chapter;
import core.users.Admin;
import core.users.Creator;
import core.users.Learner;
import database.DatabaseManager;
import managers.*;

import java.util.ArrayList;

public final class ZLearnInitializer {
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
        ArrayList<Chapter> content = new ArrayList<Chapter>(){
            {
                add(new Chapter("Les 1 - Objects", "An Object can be defined as an instance of a class.\nAn object contains an address and takes up some space in memory.\nObjects can communicate without knowing the details of each other's data or code.\nThe only necessary thing is the type of message accepted and the type of response returned by the objects.\n\nExample: A dog is an object because it has states like color, name, breed, etc. as well as behaviors like wagging the tail, barking, eating, etc. ",null,1));
                add(new Chapter("Les 2 - Class", "Collection of objects is called class. It is a logical entity.\n" + "\n" + "A class can also be defined as a blueprint from which you can create an individual object.\nClass doesn't consume any space.", null, 2));
                add(new Chapter("Les 3 - Inheritance","When one object acquires all the properties and behaviors of a parent object, it is known as inheritance.\nIt provides code reusability.\nIt is used to achieve runtime polymorphism.",null,3));
                add(new Chapter("Les 4 - Polymorphism", "If one task is performed in different ways, it is known as polymorphism.\n For example: to convince the customer differently, to draw something, for example, shape, triangle, rectangle, etc.\n" + "\n" + "In Java, we use method overloading and method overriding to achieve polymorphism.\n" + "\n" + "Another example can be to speak something; for example, a cat speaks meow, dog barks woof, etc.", null, 4));
                add(new Chapter("Les 5 - Abstraction", "Hiding internal details and showing functionality is known as abstraction.\n For example phone call, we don't know the internal processing.\n" + "\n" + "In Java, we use abstract class and interface to achieve abstraction.", null, 5));
                add(new Chapter("Les 6 - Encapsulation", "Binding (or wrapping) code and data together into a single unit are known as encapsulation.\n For example, a capsule, it is wrapped with different medicines.\n" + "\n" + "A java class is the example of encapsulation.\nJava bean is the fully encapsulated class because all the data members are private here.", null, 6));
                add(new Chapter("Les 7 - Coupling and Cohesion","Coupling\n\nCoupling refers to the knowledge or information or dependency of another class.\nIt arises when classes are aware of each other.\nIf a class has the details information of another class, there is strong coupling.\nIn Java, we use private, protected, and public modifiers to display the visibility level of a class, method, and field.\nYou can use interfaces for the weaker coupling because there is no concrete implementation.\n\nCohesion\n\nCohesion refers to the level of a component which performs a single well-defined task.\nA single well-defined task is done by a highly cohesive method.\nThe weakly cohesive method will split the task into separate parts.\nThe java.io package is a highly cohesive package because it has I/O related classes and interface.\nHowever, the java.util package is a weakly cohesive package because it has unrelated classes and interfaces.",null,7));
                add(new Chapter("Les 8 - Association", "Association represents the relationship between the objects.\nHere, one object can be associated with one object or many objects.\nThere can be four types of association between the objects:\n\n" + "    * One to One\n" + "    * One to Many\n" + "    * Many to One, and\n" + "    * Many to Many", null, 8));

            }
        };

        //add Courses
        creator2.addNewCourse("The Business School", new ArrayList<>(category),copyChapterArray(content),350);
        creator2.addNewCourse("The Java - Basics",new ArrayList<>(category),copyChapterArray(content),120);
        creator1.addNewCourse("Java 1-0-1",new ArrayList<>(category),copyChapterArray(content),0);
        admin1.addNewCourse("Code with Java", new ArrayList<>(category),copyChapterArray(content), 0);

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

    private static ArrayList<Chapter> copyChapterArray(ArrayList<Chapter> chapters){
        ArrayList<Chapter> newChapterArray = new ArrayList<>();
        for (Chapter ch:chapters) {
            newChapterArray.add(new Chapter(ch));
        }
        return newChapterArray;
    }
}
