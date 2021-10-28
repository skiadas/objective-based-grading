package obg;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.junit.Assert.*;

public class AttemptRequestTest {

    public UUID randID;
    public UUID testUUID;
    private AttemptRequestRequest request;
    private ArrayList<String> objectives;
    private List<Course> courses;
    private List<Student> students;

    @Before
    public void setUp() throws Exception {
        randID = randomUUID();
        testUUID = new UUID(0x6ba7b8109dad11d1L, 0x80b400c04fd430c8L);
        request = new AttemptRequestRequest("DoeJ24", randID, "L1");
        objectives = new ArrayList<>();
    }

    @Test
    public void canMakeAttemptRequestTest() {
        assertEquals("DoeJ24", request.userName);
        assertEquals(randID, request.courseID);
        assertEquals("L1", request.objective);
    }

    @Test
    public void canMakeAttemptRequestResponseTest() {
        AttemptRequestResponse response = new AttemptRequestResponse("DoeJ24", randID, "L1", "pending");
        assertEquals("DoeJ24", response.userName);
        assertEquals(randID, response.courseID);
        assertEquals("L1", response.objective);
        assertEquals("pending", response.attemptStatus);
    }

    @Test
    public void CheckInvalidCourseErrorTest(){
        List<Course> courses = makeCourses();
        CourseProvidingGateway testGateway = new CourseProvidingGateway(courses);
        AttemptRequestInteractor interactor = new AttemptRequestInteractor(testGateway);
        Response response = interactor.handle(request);
        assertEquals(ErrorResponse.invalidCourse(),response);
    }

    @Test
    public void checkInvalidStudentErrorTest(){
        students = makeStudents();
        InvalidStudentGateway gateway = new InvalidStudentGateway(students);
        AttemptRequestInteractor interactor = new AttemptRequestInteractor(gateway);
        Response response = interactor.handle(request);
        assertEquals(ErrorResponse.invalidStudent(), response);
    }

    @Test
    public void checkInvalidObjectiveTest(){
        objectives.add("F5");
        InvalidObjectiveGateway gateway = new InvalidObjectiveGateway(objectives);
        AttemptRequestInteractor interactor = new AttemptRequestInteractor(gateway);
        Response response = interactor.handle(request);
        assertEquals(ErrorResponse.invalidObjective(), response);
    }



    private List<Student> makeStudents(){
        Student student1 = makeStudent("student1", null);
        Student student2 = makeStudent("student2", null);
        Student student3 = makeStudent("student3", null);
        students = List.of(student1, student2, student3);
        return students;
    }

    private Student makeStudent(String userName, List<Course> studentCourses) { return new Student(UUID.randomUUID(), userName, studentCourses);}

    private List<Course> makeCourses() {
        Course course1 = makeCourse("course1");
        Course course2 = makeCourse("course2");
        Course course3 = makeCourse("course3");
        courses = List.of(course1, course2, course3);
        return courses;
    }

    private Course makeCourse(String courseName) {
        return new Course(UUID.randomUUID(), courseName);
    }

}
