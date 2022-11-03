import java.util.ArrayList;

public class Course {
    private String courseName;
    private ArrayList<String> courseCategories;
    private String courseId;
    private double rating = 0.0;
    private ArrayList<Integer> ratedBy = new ArrayList<>();
    private int creatorId;
    private int price;
    private ArrayList<Comment> comments = new ArrayList<>();
    private ArrayList<Chapter> content = new ArrayList<>();

    public Course(String courseName, ArrayList<String> courseCategory, String courseId, int creatorId, int price, ArrayList<Chapter> content) {
        this.courseName = courseName;
        this.courseCategories = courseCategory;
        this.courseId = courseId;
        this.creatorId = creatorId;
        this.price = price;
        this.content = content;
    }
    public Course(){
        this.courseId=null;
    }
    public void addContent(Chapter newChapter){
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

    public boolean isUserRated(int userId) {
        return this.ratedBy.contains(userId);
    }
    public void updateRating(int userId,int rating){
        int ratedUserCount = this.ratedBy.size();
        double newRating =  ((rating*ratedUserCount) + rating) / (ratedUserCount+1);
        this.rating = Double.parseDouble(String.format("%.1f",newRating));
        this.ratedBy.add(userId);
    }
    public void changeCourseName(String newCourseName){
        this.courseName = newCourseName;
    }
    public void addCategory(String categoryName){
        this.courseCategories.add(categoryName);
    }
    public void deleteCategory(String categoryName){
        this.courseCategories.remove(categoryName);
    }
    public void changePrice(int newPrice){
        this.price = newPrice;
        System.out.println("Price Changed");
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