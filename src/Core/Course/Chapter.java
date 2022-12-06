package Core.Course;

public class Chapter {
    private String chapterName;
    private String lesson;
    private String courseId;
    private int lessonNo;

    public Chapter(String chapterName,String lesson,String courseId,int lessonNo){
        this.chapterName = chapterName;
        this.lesson = lesson;
        this.courseId=courseId;
        this.lessonNo=lessonNo;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void changeChapterName(String newChapterName) {
        this.chapterName = newChapterName;
    }

    public String getLesson() {
        return lesson;
    }

    public void changeLesson(String lesson) {
        this.lesson = lesson;
    }

    public String getCourseId() {
        return courseId;
    }

    public int getLessonNo() {
        return lessonNo;
    }

    public void setLessonNo(int lessonNo) {
        this.lessonNo = lessonNo;
    }

    public void setCourseId(String courseId){
        this.courseId = courseId;
    }
}
