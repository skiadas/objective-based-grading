package obg;

public interface Gateway extends ViewTargetGradeGateway, AttemptRequestGateway, InstructorCourseListGateway {

    Student getStudent(String username);

    void addInstructor(Instructor instructor);
}
