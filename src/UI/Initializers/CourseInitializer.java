package UI.Initializers;

import Core.Course.Chapter;
import Core.Course.Course;
import Database.CourseDatabase;

import java.util.ArrayList;

public class CourseInitializer {
    public static void initiateCourses(){
        CourseDatabase coursedb = CourseDatabase.getInstance();
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
                add(new Chapter("Lesson 1", "Yellow is new black"));
                add(new Chapter("Lesson 2","This is minions, they have yellow skin color with round eyes!"));
                add(new Chapter("Lesson 3","Minions appears in despicable me movie and owns its franchises"));
                add(new Chapter("Lesson 4","Minions appears in despicable me movie and owns its franchises"));
                add(new Chapter("Lesson 5","Minions appears in despicable me movie and owns its franchises"));
                add(new Chapter("Lesson 6","Minions appears in despicable me movie and owns its franchises"));
                add(new Chapter("Lesson 7","Minions appears in despicable me movie and owns its franchises"));
                add(new Chapter("Lesson 8","Minions appears in despicable me movie and owns its franchises"));

            }
        };
        Course course1 = new Course("The Minions", "cs001",  category,"Ctr_1002", 200, content);
        course1.addComment("Nice Course","Lrn_1001");
        course1.addComment("Awesome Course","Lrn_1002");
        course1.addComment("Need More Improvement","Lrn_1003");
        course1.addComment("Add more content like this.","Lrn_1001");
        course1.addComment("Add more content like this.","Lrn_1102");
        course1.addComment("Add more content.","Lrn_1003");
        coursedb.addCourse(course1);
        Course course2 = new Course("The Minions 2",  "ZCourse_001", category, "Adm_001", 0, content);
        course2.addComment("Need More Improvement","Lrn_1001");
        course2.addComment("Awesome Course","Lrn_1002");
        course2.addComment("Add more content like this.","Lrn_1003");
        course2.addComment("Nice Course","Lrn_1002");
        coursedb.addCourse(course2);
        Course course3 = new Course("The Business School",  "bs001", category, "Ctr_1002", 5000, content);
        course3.addComment("Need More Improvement","Lrn_1003");
        course3.addComment("Awesome Course","Lrn_1002");
        course3.addComment("Add more content like this.","Lrn_1001");
        course3.addComment("Nice Course","Lrn_1003");
        coursedb.addCourse(course3);
        
    }
}
