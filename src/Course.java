import java.util.ArrayList;

public class Course {
    private String courseName;
    private ArrayList<String> courseCategory;
    private String courseId;
    private double rating;
    private String creatorId;
    private int price;
    private ArrayList<Comment> comments;
    private ArrayList<Chapter> content;

    void editContent(){

    }
    void editPricing(){

    }
    void editComments(){

    }
    void changeCourseName(){

    }
    void updateRating(){

    }
    void editCourseDescription(){

    }
    void updateCategory(){

    }
}

class Comment{
    private String title;
    private String body;
    private String commenter;

    void changeTitle(){

    }
    void changeBody() {

    }
}

class Chapter{
    private String chapterName;
    private String lesson;

    void changeChapterName(){

    }
    void changeLesson(){

    }
}