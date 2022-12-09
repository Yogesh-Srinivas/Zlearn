package Core.Course;

public class Course {
    private String courseName;
    private final String courseId;
    private double rating;
    private final String creatorId;
    private int price;
    private int numberOfChapters;

    //******* Constructor ***************************************************************************
    public Course(String courseName,String courseId,String creatorId,int price,int numberOfChapters){
        this.courseName = courseName;
        this.courseId = courseId;
        this.creatorId = creatorId;
        this.price = price;
        this.numberOfChapters = numberOfChapters;
    }
    //*********Getters and Setters *********************************************************************
    //******* course name ******
    public String getCourseName() {
        return courseName;
    }
    public void changeCourseName(String courseName) {
        this.courseName = courseName;
    }

    //******* course id ******

    public String getCourseId() {
        return courseId;
    }

    //******* rating ******

    public double getRating() {
        return rating;
    }
    public void setRating(double newRating){
        this.rating = newRating;
    }

    //******* creator id ******
    public String getCreatorId() {
        return creatorId;
    }

    //******* price ******
    public int getPrice() {
        return price;
    }
    public void changePrice(int price) {
        this.price = price;
    }

    //*******  no of chapter ******

    public double getCourseProgressStepValue(){
        return 100.0 / numberOfChapters;
    }
    public int getContentLength() {
        return numberOfChapters;
    }
    public int getNumberOfChapters() {
        return numberOfChapters;
    }
    public void setNumberOfChapters(int numberOfChapters) {
        this.numberOfChapters = numberOfChapters;
    }
}
