package db;

import obg.core.entity.*;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Test;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.junit.Assert.*;

public class TestDb {

    private final SqlBackedGatewayFactory gatewayFactory = SqlBackedGatewayFactory.getInstance();

    @Test(expected = javax.persistence.PersistenceException.class)
    public void testCannotAddTwoStudentsWithSameUUID() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("test");
        EntityManager em = factory.createEntityManager();
        UUID studentId = UUID.randomUUID();
        Student student = new Student(studentId, UUID.randomUUID().toString());
        Student student2 = new Student(studentId, UUID.randomUUID().toString());
        em.getTransaction().begin();
        em.persist(student);
        em.persist(student2);
        em.getTransaction().commit();
    }

    @Test
    public void testCanConnect() {
        UUID studentId = UUID.randomUUID();
        Student student = new Student(studentId, UUID.randomUUID().toString());
        gatewayFactory.doWithGateway(gateway -> gateway.save(student));
    }

    @Test
    public void canFindStudentByUsername() {
        UUID studentId = UUID.randomUUID();
        String username = studentId.toString();
        Student student = new Student(studentId, username);
        gatewayFactory.doWithGateway(gateway -> {
            gateway.save(student);
            gateway.save(randomStudent());
            gateway.save(randomStudent());
        });
        gatewayFactory.doWithGateway(gateway -> {
            Student retrievedStudent = gateway.getStudent(username);
            assertEquals(username, retrievedStudent.userName);
            assertEquals(student, retrievedStudent);
        });
    }

    @Test
    public void canCreateNewInstructor() {
        String instructorId = UUID.randomUUID().toString();
        String first = "first";
        String last = "last";
        Instructor instructor = new Instructor(instructorId, first, last);
        gatewayFactory.doWithGateway(gateway -> gateway.save(instructor));
        gatewayFactory.doWithGateway(gateway -> {
            Instructor retrievedInstructor = gateway.getInstructor(instructor.getInstructorId());
            assertEquals(instructor, retrievedInstructor);
        });
    }

    @Test
    public void canAddCourses() {
        Course course = new Course(UUID.randomUUID(), "course name");
        gatewayFactory.doWithGateway(gateway -> gateway.save(course));
        gatewayFactory.doWithGateway(gateway -> {
            Course retrievedCourse = gateway.getCourse(course.getCourseId());
            assertEquals(course.courseName, retrievedCourse.courseName);
            assertEquals(course, retrievedCourse);
        });
    }

    @Test
    public void canEnrollStudentsInCourses() {
        Student student1 = randomStudent();
        Student student2 = randomStudent();
        Course course1 = new Course(UUID.randomUUID(), "course1");
        Course course2 = new Course(UUID.randomUUID(), "course2");
        gatewayFactory.doWithGateway(gateway -> {
            gateway.save(student1);
            gateway.save(student2);
            gateway.save(course1);
            gateway.save(course2);
        });
        Enrollment enrollment1 = new Enrollment(course1, student1, "today", false);
        Enrollment enrollment2 = new Enrollment(course1, student2, "today", true);
        Enrollment enrollment3 = new Enrollment(course2, student2, "today", true);
        gatewayFactory.doWithGateway(gateway -> {
            gateway.save(enrollment1);
            gateway.save(enrollment2);
            gateway.save(enrollment3);
        });
        gatewayFactory.doWithGateway(gateway -> {
            EntityManager em = ((SqlBackedGateway) gateway).getEntityManager();
            String name = student1.userName;
            boolean getAllEnrollments = false;
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Enrollment> cq = cb.createQuery(Enrollment.class);
            Root<Enrollment> e = cq.from(Enrollment.class);
            cq.select(e);
            System.out.println("WERE HERE!!!!");
            if (!getAllEnrollments) {
                cq.where(cb.equal(e.get("student").get("userName"), name));
            }
            TypedQuery<Enrollment> query = em.createQuery(cq);
            List<Enrollment> enrollments = query.getResultList();
            System.out.println(enrollments);
        });
    }

    @Test
    public void canGetStudentCourses(){
        UUID student1Id = randomUUID();
        Student student1 = new Student(student1Id, UUID.randomUUID().toString() + student1Id);
        UUID student2Id = randomUUID();
        Student student2 = new Student(student2Id, UUID.randomUUID().toString() + student2Id);
        UUID course1ID = randomUUID();
        Course course1 = new Course(course1ID, "course1" + course1ID);
        UUID course2ID = randomUUID();
        Course course2 = new Course(course2ID, "course2" + course2ID);
        gatewayFactory.doWithGateway(gateway -> {
            gateway.save(student1);
            gateway.save(student2);
            gateway.save(course1);
            gateway.save(course2);
        });
        Enrollment enrollment1 = new Enrollment(course1, student1, "today", false);
        Enrollment enrollment2 = new Enrollment(course1, student2, "today", false);
        Enrollment enrollment3 = new Enrollment(course2, student2, "today", false);
        gatewayFactory.doWithGateway(gateway -> {
            gateway.save(enrollment1);
            gateway.save(enrollment2);
            gateway.save(enrollment3);
        });
        gatewayFactory.doWithGateway(gateway -> {
            List<Course> retrievedStudentCourses = gateway.getStudentCourses(student2.userName);
            List<Course> retrievedStudentCourses_2 = gateway.getStudentCourses(student1.userName);
            assertEquals(List.of(course1, course2), retrievedStudentCourses);
            assertEquals(List.of(course1), retrievedStudentCourses_2);
        });
    }

    @Test
    public void canGetEnrollmentFromGateway(){
        UUID student1Id = randomUUID();
        Student student1 = new Student(student1Id, UUID.randomUUID().toString() + student1Id);
        UUID student2Id = randomUUID();
        Student student2 = new Student(student2Id, UUID.randomUUID().toString() + student2Id);
        UUID course1ID = randomUUID();
        Course course1 = new Course(course1ID, "course1" + course1ID);
        UUID course2ID = randomUUID();
        Course course2 = new Course(course2ID, "course2" + course2ID);
        gatewayFactory.doWithGateway(gateway -> {
            gateway.save(student1);
            gateway.save(student2);
            gateway.save(course1);
            gateway.save(course2);
        });
        Enrollment enrollment1 = new Enrollment(course1, student1, "today", false);
        Enrollment enrollment3 = new Enrollment(course2, student2, "today", false);
        gatewayFactory.doWithGateway(gateway -> {
            gateway.save(enrollment1);
            gateway.save(enrollment3);
        });
        gatewayFactory.doWithGateway(gateway -> assertEquals(enrollment1,  gateway.getEnrollment(course1.getCourseId(), student1Id.toString())));
    }

    @Test
    public void canRemoveStudentFromCourse(){
        UUID student1Id = randomUUID();
        Student student1 = new Student(student1Id, UUID.randomUUID().toString() + student1Id);
        UUID student2Id = randomUUID();
        Student student2 = new Student(student2Id, UUID.randomUUID().toString() + student2Id);
        UUID course1ID = randomUUID();
        Course course1 = new Course(course1ID, "course1" + course1ID);
        UUID course2ID = randomUUID();
        Course course2 = new Course(course2ID, "course2" + course2ID);
        Instructor instructor = new Instructor(UUID.randomUUID().toString(), "first", "last");
        course1.setInstructor(instructor);
        course2.setInstructor(instructor);
        gatewayFactory.doWithGateway(gateway -> {
            gateway.save(instructor);
            gateway.save(student1);
            gateway.save(student2);
            gateway.save(course1);
            gateway.save(course2);
        });
        Enrollment enrollment1 = new Enrollment(course1, student1, "today", false);
        Enrollment enrollment2 = new Enrollment(course1, student2, "today", false);
        Enrollment enrollment3 = new Enrollment(course2, student2, "today", false);
        gatewayFactory.doWithGateway(gateway -> {
            gateway.save(enrollment1);
            gateway.save(enrollment2);
            gateway.save(enrollment3);
        });
        gatewayFactory.doWithGateway(gateway -> {
            gateway.getEnrollment(course1ID, student1Id.toString()).removeStudent();
            List<Course> retrievedStudentCourses = gateway.getStudentCourses(student2.userName);
            List<Course> retrievedStudentCourses_2 = gateway.getStudentCourses(student1.userName);
            assertEquals(List.of(course1, course2), retrievedStudentCourses);
            assertEquals(List.of(), retrievedStudentCourses_2);
        });
    }

    @Test
    public void CanFindInstructorById(){
        Instructor instructor = new Instructor("newId", "Joe", "Brown");
        gatewayFactory.doWithGateway(gateway -> {
            gateway.save(instructor);
            gateway.save(new Instructor("new1", "new", "1"));
            gateway.save(new Instructor("new2", "new", "2"));
        });
        gatewayFactory.doWithGateway(gateway -> {
        Instructor retrievedInstructor = gateway.getInstructor(instructor.getInstructorId());
        assertEquals(instructor.getInstructorId(), retrievedInstructor.getInstructorId());
        });
    }

    @Test
    public void canGetDifferentCoursesForDifferentInstructors(){
        Instructor instructor1 = new Instructor("newId_1", "new1", "name1");
        Instructor instructor2 = new Instructor("newId_2", "new2", "name2");
        Course course1 = new Course(UUID.randomUUID(), "course_1", new ArrayList<>());
        Course course2 = new Course(UUID.randomUUID(), "course_2", new ArrayList<>());
        Course course3 = new Course(UUID.randomUUID(), "course_3", new ArrayList<>());
        Course course4 = new Course(UUID.randomUUID(), "course_4", new ArrayList<>());
        course1.setInstructor(instructor1);
        course2.setInstructor(instructor2);
        course3.setInstructor(instructor1);
        course4.setInstructor(instructor2);
        gatewayFactory.doWithGateway(gateway -> {
            gateway.save(instructor1);
            gateway.save(instructor2);
            gateway.save(course1);
            gateway.save(course2);
            gateway.save(course3);
            gateway.save(course4);
        });
        gatewayFactory.doWithGateway(gateway -> {
            List<Course> retrievedCourseList_1 = gateway.getCoursesTaughtBy(instructor1);
            List<Course> retrievedCourseList_2 = gateway.getCoursesTaughtBy(instructor2);
            assertEquals(List.of(course1, course3), retrievedCourseList_1);
            assertEquals(List.of(course2, course4), retrievedCourseList_2);
        });
    }

    @Test
    public void canAddEnrollment() {
        Course course = new Course(UUID.randomUUID(),"course");
        Student student = randomStudent();
        Enrollment enrollment = new Enrollment(course, student);
        gatewayFactory.doWithGateway(gateway ->  {
            gateway.save(course);
            gateway.save(student);
        });
        gatewayFactory.doWithGateway(gateway -> gateway.saveEnrollment(enrollment));
        gatewayFactory.doWithGateway(gateway -> {
            Enrollment savedEnrollment = gateway.getEnrollment(course.getCourseId(), student.studentId.toString());
            assertNotNull(savedEnrollment);
        });
    }

    @Test
    public void canSaveInstructor() {
        Instructor instructor1 = new Instructor( "instructor1", "Mrs.", "James");
        Instructor instructor2 = new Instructor( "instructor2", "Mrs.", "Kiyoko");
        gatewayFactory.doWithGateway(gateway -> {
            gateway.save(instructor1);
            gateway.save(instructor2);
        });
        gatewayFactory.doWithGateway(gateway -> {
            Instructor retrievedInstructor1 = gateway.getInstructor(instructor1.getInstructorId());
            Instructor retrievedInstructor2 = gateway.getInstructor(instructor2.getInstructorId());
            assertSame(instructor1.getInstructorId(), retrievedInstructor1.getInstructorId());
            assertSame(instructor2.getInstructorId(), retrievedInstructor2.getInstructorId());
        });
    }


    @Test
    public void canSaveAttempt() {
        Course course1 = new Course(UUID.randomUUID(),"course1");
        Course course2 = new Course(UUID.randomUUID(),"course2");
        Student student1 = randomStudent();
        Student student2 = randomStudent();
        Enrollment enrollment1 = new Enrollment(course1, student2);
        Enrollment enrollment2 = new Enrollment(course2, student2);
        Attempt attempt1 = new Attempt("obj1", 1, enrollment1);
        Attempt attempt2 = new Attempt("obj2",2, enrollment2);
        gatewayFactory.doWithGateway(gateway -> {
            gateway.save(student1);
            gateway.save(student2);
            gateway.save(course1);
            gateway.save(course2);
            gateway.save(enrollment1);
            gateway.save(enrollment2);
            gateway.save(attempt1);
            gateway.save(attempt2);
        });
        gatewayFactory.doWithGateway(gateway -> {
            Attempt retrievedAttempt1 = gateway.getAttempt(attempt1.getAttemptId());
            Attempt retrievedAttempt2 = gateway.getAttempt(attempt2.getAttemptId());
            assertEquals(attempt1.getAttemptId(), retrievedAttempt1.getAttemptId());
            assertEquals(attempt2.getAttemptId(), retrievedAttempt2.getAttemptId());
        });
    }

    @Test
    public void canAddStudentsToSystem() {
        UUID studentId1 = randomUUID();
        Student student1 = new Student(studentId1, UUID.randomUUID().toString());
        UUID studentId2 = randomUUID();
        Student student2 = new Student(studentId2, UUID.randomUUID().toString());
        gatewayFactory.doWithGateway(gateway -> {
                    gateway.addStudent(student1);
                    gateway.addStudent(student2);
                }
        );
        gatewayFactory.doWithGateway(gateway -> {
            Student retrievedStudent1 = gateway.getStudent(studentId1);
            Student retrievedStudent2 = gateway.getStudent(studentId2);
            assertEquals(retrievedStudent1, student1);
            assertEquals(retrievedStudent2, student2);
        });

    }

    @Test(expected = Exception.class)
    public void errorThrownWhenUserNameTaken(){
        String userName = randomUUID().toString();
        Student student1 = new Student(randomUUID(), userName);
        Student student2 = new Student(randomUUID(), userName);
        gatewayFactory.doWithGateway(gateway -> {
                    gateway.addStudent(student1);
                }
        );
        gatewayFactory.doWithGateway(gateway -> {
                    gateway.addStudent(student2);
                }
        );
    }

    private void printQueryResults(EntityManager em, String query) {
        List<Object[]> resultList = em.createQuery(query).getResultList();
        printList(query, resultList);
    }

    private void printList(String query, List<Object[]> resultList) {
        System.out.println("====================================");
        System.out.println("QUERY: " + query);
        System.out.println(resultList.size() + " matches found.");
        resultList.forEach((Object[] row) -> {
            for (Object entry : row) {
                System.out.print(entry + "\t");
            }
            System.out.println();
        });
        System.out.println("====================================");
    }

    private Student randomStudent() {
        return new Student(UUID.randomUUID(), UUID.randomUUID().toString());
    }
}

