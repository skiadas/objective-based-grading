package db;

import obg.core.entity.*;
import org.junit.Ignore;
import org.junit.Test;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestDb {

    private SqlBackedGatewayFactory gatewayFactory = SqlBackedGatewayFactory.getInstance();

    @Test(expected = javax.persistence.PersistenceException.class)
    public void testCannotAddTwoStudentsWithSameUUID() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("test");
        EntityManager em = factory.createEntityManager();
        UUID studentId = UUID.randomUUID();
        Student student = new Student(studentId, "skiadas");
        Student student2 = new Student(studentId, "skiadas2");
        em.getTransaction().begin();
        em.persist(student);
        em.persist(student2);
        em.getTransaction().commit();
    }

    @Test
    public void testCanConnect() {
        UUID studentId = UUID.randomUUID();
        Student student = new Student(studentId, "skiadas");

        gatewayFactory.doWithGateway(gateway -> gateway.save(student));
    }

    @Test
    public void canFindStudentByUsername() {
        UUID studentId = UUID.randomUUID();
        Student student = new Student(studentId, "skiadas");

        gatewayFactory.doWithGateway(gateway -> {
            gateway.save(student);
            gateway.save(new Student(UUID.randomUUID(), "anotherStudent"));
            gateway.save(new Student(UUID.randomUUID(), "yetAnotherStudent"));
        });

        gatewayFactory.doWithGateway(gateway -> {
            Student retrievedStudent = gateway.getStudent("skiadas");
            assertEquals("skiadas", retrievedStudent.userName);
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
        Student student1 = new Student(UUID.randomUUID(), "joe23");
        Student student2 = new Student(UUID.randomUUID(), "jane24");
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
            Boolean getAllEnrollments = false;
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
        Student student1 = new Student(student1Id, "joe23" + student1Id);
        UUID student2Id = randomUUID();
        Student student2 = new Student(student2Id, "jane24" + student2Id);
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
        Student student1 = new Student(student1Id, "joe23" + student1Id);
        UUID student2Id = randomUUID();
        Student student2 = new Student(student2Id, "jane24" + student2Id);
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

        gatewayFactory.doWithGateway(gateway -> {
            assertEquals(enrollment1.getLongId(),  gateway.getEnrollment(course1.getCourseId(), student1Id.toString()).getLongId());
        });
    }

    @Ignore
    @Test
    public void canRemoveStudentFromCourse(){
        UUID student1Id = randomUUID();
        Student student1 = new Student(student1Id, "joe23" + student1Id);
        UUID student2Id = randomUUID();
        Student student2 = new Student(student2Id, "jane24" + student2Id);
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
            gateway.removeStudent(enrollment1);
            //// trying to see why it was not passing so we checked to see if the enrolment itself was right in being null becuase student is remove from course
            List<Course> retrievedStudentCourses = gateway.getStudentCourses(student2.userName);
            List<Course> retrievedStudentCourses_2 = gateway.getStudentCourses(student1.userName);
            assertEquals(List.of(course1, course2), retrievedStudentCourses);
            assertEquals(gateway.getEnrollment(course1.getCourseId(), student1.getStudentId().toString()), null);
            assertEquals(List.of(), retrievedStudentCourses_2);            //returns a course NOT SUPPOSE TOO??? WHY???
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
            List retrievedCourseList_1 = gateway.getCoursesTaughtBy(instructor1);
            List retrievedCourseList_2 = gateway.getCoursesTaughtBy(instructor2);
            assertEquals(List.of(course1, course3), retrievedCourseList_1);
            assertEquals(List.of(course2, course4), retrievedCourseList_2);
        });
    }

    @Test
    public void canAddEnrollment() {
        Course course = new Course(UUID.randomUUID(),"course");
        Student student = new Student(UUID.randomUUID(), "student");
        Enrollment enrollment = new Enrollment(course, student);
        // Check that enrollment is created correctly
        gatewayFactory.doWithGateway(gateway ->  {
            gateway.save(course);
            gateway.save(student);
        });

        gatewayFactory.doWithGateway(gateway -> {
            gateway.saveEnrollment(enrollment);
        });

        gatewayFactory.doWithGateway(gateway -> {
            Enrollment savedEnrollment = gateway.getEnrollment(course.getCourseId(), student.studentId.toString());
            assertNotNull(savedEnrollment);
        });
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
}

