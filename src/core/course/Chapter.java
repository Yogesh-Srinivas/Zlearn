package core.course;

public final class Chapter {
    private String chapterName;
    private String lesson;
    private String courseId;
    private int lessonNo;

    //******* Constructor *******************************

    public Chapter(String chapterName,String lesson,String courseId,int lessonNo){
        this.chapterName = chapterName;
        this.lesson = lesson;
        this.courseId=courseId;
        this.lessonNo=lessonNo;
    }

    //Copy Constructor
    public Chapter(Chapter chapter){
        this.chapterName = chapter.chapterName;
        this.lesson = chapter.lesson;
        this.courseId=chapter.courseId;
        this.lessonNo=chapter.lessonNo;
    }
    //******* Getters and Setters *******************************

    //******* ch name ******
    public String getChapterName() {
        return chapterName;
    }
    public void changeChapterName(String newChapterName) {
        this.chapterName = newChapterName;
    }
    //******* lesson ******
    public String getLesson() {
        return lesson;
    }
    public void changeLesson(String lesson) {
        this.lesson = lesson;
    }
    //******* courseId ******
    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId){
        this.courseId = courseId;
    }

    //******* lesson number ******
    public int getLessonNo() {
        return lessonNo;
    }
    public void setLessonNo(int lessonNo) {
        this.lessonNo = lessonNo;
    }

}
