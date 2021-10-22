package obg;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Course {

    static UUID CourseID;
    private ArrayList Students;
    private ArrayList Objectives;
    public Course(UUID courseID,ArrayList students,ArrayList objectives) {
        Students=students;
        Objectives= objectives;
        CourseID=courseID;

    }
    public Boolean checkValidStudent(String UserName){

        return true;
    }
}
