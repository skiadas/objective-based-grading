package db;

import obg.core.entity.Course;
import obg.core.entity.Enrollment;
import obg.core.entity.Instructor;
import obg.core.entity.Student;
import org.junit.Test;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class TestDb {

    private DBBackedGatewayFactory gatewayFactory = DBBackedGatewayFactory.getInstance();

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
            EntityManager em = ((DBBackedGateway) gateway).getEntityManager();
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
    public void canGetCoursesTaughtByInstructor(){
        Instructor instructor = new Instructor("newId", "Joe", "Brown");
        Course course1 = new Course(UUID.randomUUID(), "course1", new ArrayList<>());
        Course course2 = new Course(UUID.randomUUID(), "course2", new ArrayList<>());
        Course course3 = new Course(UUID.randomUUID(), "course3", new ArrayList<>());
        course1.setInstructor(instructor);
        course2.setInstructor(instructor);
        course3.setInstructor(instructor);
        gatewayFactory.doWithGateway(gateway -> {
            gateway.save(instructor);
            gateway.save(course1);
            gateway.save(course2);
            gateway.save(course3);
        });

        gatewayFactory.doWithGateway(gateway -> {
            List retrievedCourseList = gateway.getCoursesTaughtBy(instructor);
            assertEquals(List.of(course1, course2, course3), retrievedCourseList);
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
