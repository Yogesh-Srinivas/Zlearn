package core.course;

public final class Comment {
    private final String courseId;
    private final String comment;
    private final String commenter;

    //******* Constructor *******************************
    public Comment(String comment, String commenter,String courseId) {
        this.comment = comment;
        this.commenter = commenter;
        this.courseId = courseId;
    }

    //Copy Constructor
    public Comment(Comment comment){
        this.comment = comment.comment;
        this.commenter = comment.commenter;
        this.courseId = comment.courseId;
    }
    //******* Getters and Setters *******************************

    public String getCourseId(){return courseId;}

    public String getComment() {
        return comment;
    }

    public String getCommenter() {
        return commenter;
    }
}
