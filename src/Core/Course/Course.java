package Core.Course;

import java.util.ArrayList;

public class Course {
    private String courseName;
    private ArrayList<String> courseCategories;
    private final String courseId;
    private double rating;
    private ArrayList<String> ratedBy = new ArrayList<>();
    private final String creatorId;
    private int price;
    private ArrayList<Comment> comments = new ArrayList<>();
    private ArrayList<Chapter> content;

    //***********************************************************************************
    public Course(String courseName,String courseId,ArrayList<String> courseCategories,String creatorId,int price,ArrayList<Chapter> content){
        this.courseName = courseName;
        this.courseId = courseId;
        this.courseCategories = courseCategories;
        this.creatorId = creatorId;
        this.price = price;
        this.content = content;

    }
    public String getCourseName() {
        return courseName;
    }

    public String getCourseId() {
        return courseId;
    }

    public double getRating() {
        return rating;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public int getPrice() {
        return price;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public ArrayList<Chapter> getContent() {
        return content;
    }

    public ArrayList<String> getCourseCategories() {
        return courseCategories;
    }

    public boolean isRatedBy(String userId){
        return this.ratedBy.contains(userId);
    }

    public ArrayList<String> getCourseLearnings(){
        ArrayList<String> learnings = new ArrayList<>();
        for(Chapter ch : this.content){
            learnings.add(ch.getChapterName());
        }
        return learnings;
    }

    public double getCourseProgressStepValue(){
        return 100.0 / this.content.size();
    }
    //***********************************************************************************

    public void changeCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void changePrice(int price) {
        this.price = price;
    }

    public void addCourseCategory(String category){
        this.courseCategories.add(category);
    }

    public void removeCourseCategory(String category){
        this.courseCategories.remove(category);
    }

    public void updateRating(double rating,String userId){
        if(!this.ratedBy.contains(userId)){
            int ratedUserCount = this.ratedBy.size();
            double newRating =  ((rating*ratedUserCount) + rating) / (ratedUserCount+1);
            this.rating = Double.parseDouble(String.format("%.1f",newRating));
            this.ratedBy.add(userId);
        }
    }

    public void addComment(String comment,String commentor){
        this.comments.add(new Comment(comment,commentor));
    }

    public void addContent(Chapter chapter){
        this.content.add(chapter);
    }

    public void deleteContent(int chapterIndex){
        this.content.remove(chapterIndex);
    }

    public int getContentLength() {
        return this.content.size();
    }

    public Chapter getChapter(int chapterIndex) {
        return this.content.get(chapterIndex);
    }

    public void changeChapterName(String newChapterName, int chapterIndex) {
        this.content.get(chapterIndex).changeChapterName(newChapterName);
    }
    public void changeLesson(String newLesson, int chapterIndex) {
        this.content.get(chapterIndex).changeLesson(newLesson);
    }

}
