import java.util.ArrayList;

public class Course {
    private String courseName;
    private ArrayList<String> courseCategory;
    private String courseId;
    private double rating;
    private String creatorId;
    private int price;
    private ArrayList<Comment> comments = new ArrayList<>();
    private ArrayList<Chapter> content = new ArrayList<>();

    public Course(String courseName, ArrayList<String> courseCategory, String courseId, double rating, String creatorId, int price, ArrayList<Chapter> content) {
        this.courseName = courseName;
        this.courseCategory = courseCategory;
        this.courseId = courseId;
        this.rating = rating;
        this.creatorId = creatorId;
        this.price = price;
        this.content = content;
    }
    public Course(){
        this.courseId=null;
    }
    void editContent(){

    }
    void addContent(Chapter newChapter){
        this.content.add(newChapter);
    }
    public Chapter getChapter(int ind){
        return this.content.get(ind);
    }
    public int getContentLength(){
        return this.content.size();
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

    public String getCourseId() {
        return courseId;
    }

    public double getRating() {
        return rating;
    }

    public String getCourseName() {
        return courseName;
    }
    public double getCourseProgressStepValue(){
        return 100.0 / this.content.size();
    }
}

class Comment{
    private String title;
    private String body;
    private String commenter;

    Comment(String title, String body, String commenter) {
        this.title = title;
        this.body = body;
        this.commenter = commenter;
    }

    void changeTitle(){

    }
    void changeBody() {

    }
}

class Chapter{
    private String chapterName;
    private String lesson;
    Chapter(String chapterName,String lesson){
        this.chapterName=chapterName;
        this.lesson=lesson;
    }
    void changeChapterName(){

    }
    void changeLesson(){

    }

    public String getChapterName() {
        return chapterName;
    }

    public String getLesson() {
        return lesson;
    }
}