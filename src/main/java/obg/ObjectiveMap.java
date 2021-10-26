package obg;

import java.util.HashMap;

public class ObjectiveMap {

    private static String studentId;
    private static String[] obj;
    private static String getStudentId;
    int grade;

    public static void main(String[] args) {
        String[][] getObjectiveAndGrade = {{"L1", "4"}, {"S1", "2"}};
        ObjectiveMap.obj = args;
        HashMap<String, String[][]> map = new HashMap<>();

        map.put(getStudentId, getObjectiveAndGrade);
        map.put(getStudentId, getObjectiveAndGrade);
        map.put(getStudentId, getObjectiveAndGrade);

        //System.out.println("Size of map is: " + map.size());
        //System.out.println(map);

    }

    public int getGrade(){return grade;}

    public String getStudentId() {return studentId;}
}
