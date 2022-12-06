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
        coursedb.addComment("Nice Course","cs001","Lrn_1001");
        coursedb.addComment("Awesome Course","cs001","Lrn_1002");
        coursedb.addComment("Need More Improvement","cs001","Lrn_1003");
        coursedb.addComment("Add more content like this.","cs001","Lrn_1001");
        coursedb.addComment("Add more content like this.","cs001","Lrn_1102");
        coursedb.addComment("Add more content.","cs001","Lrn_1003");
        coursedb.addCourse(course1);
        Course course2 = new Course("The Minions 2",  "ZCourse_001", category, "Adm_001", 0, content);
        coursedb.addComment("Need More Improvement","ZCourse_001","Lrn_1001");
        coursedb.addComment("Awesome Course","ZCourse_001","Lrn_1002");
        coursedb.addComment("Add more content like this.","ZCourse_001","Lrn_1003");
        coursedb.addComment("Nice Course","ZCourse_001","Lrn_1002");
        coursedb.addCourse(course2);
        Course course3 = new Course("The Business School",  "bs001", category, "Ctr_1002", 5000, content);
        coursedb.addComment("Need More Improvement","bs001","Lrn_1003");
        coursedb.addComment("Awesome Course","bs001","Lrn_1002");
        coursedb.addComment("Add more content like this.","bs001","Lrn_1001");
        coursedb.addComment("Nice Course","bs001","Lrn_1003");
        coursedb.addCourse(course3);
        
    }
}
