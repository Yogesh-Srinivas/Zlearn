package core.course;

public final class RatedUser {
    private final String courseId;
    private final String userId;
    private final int rating;


    //******* Constructor *******************************
    public RatedUser(String courseId, String userId, int rating) {
        this.courseId = courseId;
        this.userId = userId;
        this.rating = rating;
    }

    //******* Getters and Setters *******************************
    //******* course id ******

    public String getCourseId() {
        return courseId;
    }

    //******* user id ******

    public String getUserId() {
        return userId;
    }

    //********* rating ******
    public int getRating() {
        return rating;
    }


}
