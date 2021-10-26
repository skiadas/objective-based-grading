package obg;

import java.util.HashMap;

public class ObjectiveMap {

    private static String[] obj;
    int grade;

    public static void main(String[] args) {
        ObjectiveMap.obj = args;
        HashMap<String, Integer> map = new HashMap<>();

        map.put("L1", null);
        map.put("L2", null);
        map.put("L3", null);

        //System.out.println("Size of map is: " + map.size());
        System.out.println(map);

    }

    public int getGrade(){
        return grade;
    }

}
