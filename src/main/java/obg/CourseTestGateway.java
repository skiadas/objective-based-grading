package obg;

public class CourseTestGateway implements Gateway{
    private Course providedCourse;

    public CourseTestGateway(Course providedCourse) {
        this.providedCourse = providedCourse;
    }



    public boolean isValidCourse(Course requestCourse){
        return requestCourse.equals(providedCourse);
    }





}