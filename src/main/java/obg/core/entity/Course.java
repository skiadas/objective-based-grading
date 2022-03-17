package obg.core.entity;

import javax.persistence.*;
import java.util.*;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic
    @Column(columnDefinition = "uuid", unique = true)
    public UUID courseId;

    @Basic
    public String courseName;

    @ManyToOne
    public Instructor instructor;

    @ManyToMany
    public List<Student> students;

    @OneToMany(mappedBy = "course")
    public List<Enrollment> enrollments;


    // TODO: Fix it
    @Transient
    public ArrayList<String> objectives = new ArrayList<>();

    @Transient
    public GradeBreakPoints gradeBreaks = new GradeBreakPoints();

    @Transient
    public EnumMap<ObjectiveGroup, String> objectiveByGroups = new EnumMap<>(ObjectiveGroup.class);

    private Course() {
    }

    public Course(UUID courseId, String courseName, List<Student> students) {
        this.students = students;
        this.courseId = courseId;
        this.courseName = courseName;
    }

    public Course(UUID courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
        students = new ArrayList<>();
    }

    public Course(String courseName) {
        this(UUID.randomUUID(), courseName);
    }

    public UUID getCourseId() {
        return courseId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course that = (Course) o;
        return courseId.equals(that.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, courseName, students);
    }

    public boolean isValidLetterGrade(String letterGrade) {
        List<String> letterGrades = new ArrayList<>(List.of("A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "F"));
        return letterGrades.contains(letterGrade);
    }

    public boolean isCourseInstructor(Instructor instructor) {
        return this.instructor == instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public boolean isValidObjective(String objective) {
        List<String> objectiveList = new ArrayList<>(List.of("L1", "L2", "S1", "S2", "C1", "C2"));
        return objectiveList.contains(objective);
    }

    public String getObjectivesFor(ObjectiveGroup group) {

        if (objectiveByGroups.containsKey(group)) {
            return objectiveByGroups.get(group);
        }
        else {
            throw new RuntimeException(group.toString() + ": Not Found");
        }
    }

    public void addObjective(ObjectiveGroup group, String obj) {
        objectiveByGroups.put(group, obj);
    }

    public void removeObjective(ObjectiveGroup obj) {
        if (objectiveByGroups.containsKey(obj)) {
            objectiveByGroups.remove(obj);
        }
    }

    public boolean addStudent(Student student) {
        return students.add(student);
    }
}