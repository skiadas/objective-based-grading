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
    public List<Enrollment> enrollments = new ArrayList<>();


    // TODO: Fix it
    @Transient
    public ArrayList<String> objectives = new ArrayList<>();

    @Transient
    public GradeBreakPoints gradeBreaks = GradeBreakPoints.prePopulated();

    @Transient
    public EnumMap<ObjectiveGroup, ArrayList<String>> objectivesByGroup = new EnumMap<>(ObjectiveGroup.class);

    protected Course() {
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
    public Course(UUID courseId, String courseName, List<Student> students,
                  EnumMap<ObjectiveGroup,ArrayList<String>> objectivslist){

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
        List<String> letterGrades = new ArrayList<>(
                List.of("A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "F"));
        return letterGrades.contains(letterGrade);
    }

    public boolean isCourseInstructor(Instructor instructor) {
        return this.instructor == instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }


    public ArrayList<String> getObjectivesFor(ObjectiveGroup group) {
        if (isValidObjectiveGroup(group)) {
            System.out.println(objectives);

            return objectivesByGroup.get(group);
        } else {
            throw new RuntimeException(group.toString() + ": Not Found");
        }
    }

    public boolean isValidObjectiveGroup(ObjectiveGroup group) {
        return objectivesByGroup.containsKey(group);
    }

    public void addObjective(ObjectiveGroup group, String obj) {
        if(objectivesByGroup.containsKey(group)) {
            objectivesByGroup.get(group).add(objectivesByGroup.get(group).size()-1, obj);
            ArrayList<String> objs = objectivesByGroup.get(group);
            objectivesByGroup.put(group, objs);
        }
        else{
            ArrayList<String> objs = new ArrayList<>();
            objs.add(0, obj);
            objectivesByGroup.put(group, objs);
        }
    }

    public void removeObjective(ObjectiveGroup obj) {
        objectivesByGroup.remove(obj);
    }

    public boolean isValidObjective(String objective) {
        for (ObjectiveGroup group :objectivesByGroup.keySet()) {
            if(objectivesByGroup.get(group).contains(objective)){
                return true;
            }
        }
        return false;
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public void addEnrollment(Enrollment enrollment) {
        enrollments.add(enrollment);
    }

    public boolean hasStudent(Student student) {
        for (Enrollment e : enrollments) {
            if (e.getStudent().equals(student)) {
                return true;
            }
        }
        return false;
    }
}