import java.util.ArrayList;

public class Course {
    private String courseName;
    private ArrayList<String> courseCategories;
    private String courseId;
    private double rating;
    private int creatorId;
    private int price;
    private ArrayList<Comment> comments = new ArrayList<>();
    private ArrayList<Chapter> content = new ArrayList<>();

    public Course(String courseName, ArrayList<String> courseCategory, String courseId, double rating, int creatorId, int price, ArrayList<Chapter> content) {
        this.courseName = courseName;
        this.courseCategories = courseCategory;
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

    public void addComment(String comment,int commenter){
        Comment newComment = new Comment(comment,commenter);
        this.comments.add(newComment);
    }
    public ArrayList<Comment> getComments(){
        return this.comments;
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

    public int getCreatorId() {
        return creatorId;
    }

    public int getPrice() {
        return price;
    }
    public ArrayList<String> getCourseLearnings(){
        ArrayList<String> learnings = new ArrayList<>();
        for(Chapter ch : this.content){
            learnings.add(ch.getChapterName());
        }
        return learnings;
    }

    public ArrayList<String> getCourseCategories() {
        return courseCategories;
    }
}

class Comment{
    private String comment;
    private int commenter;

    Comment(String comment, int commenter) {
        this.comment = comment;
        this.commenter = commenter;
    }

    public String getComment() {
        return comment;
    }

    public int getCommenter() {
        return commenter;
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