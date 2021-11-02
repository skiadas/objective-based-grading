package obg;

import java.util.EnumMap;

public class ViewTargetGradeInteractor {
    private final ViewTargetGradeGateway gateway;
    private Course course;

    public ViewTargetGradeInteractor(ViewTargetGradeGateway gateway) {
        this.gateway = gateway;
    }

    public Response handle(ViewTargetGradeRequest request) {
        course = gateway.getCourse(request.courseId);
        TargetGradeRequirementsResponse targetGradeRequirementsResponse = new TargetGradeRequirementsResponse(request.letterGrade);
        if (course == null) {
            return ErrorResponse.invalidCourse();
        } else if (!gateway.isValidLetterGrade(request.letterGrade)) {
            return ErrorResponse.invalidLetterGrade();
        }
        TargetGradeRequirementsResponse.objectiveRequirements.putAll(PopulateGradeRequirements(request.letterGrade));
        return targetGradeRequirementsResponse;
    }

    private EnumMap<ObjectiveGroup, Integer> PopulateGradeRequirements(String letterGrade) {
        final EnumMap<ObjectiveGroup, Integer> objectiveRequirements = new EnumMap<>(ObjectiveGroup.class);
        switch (letterGrade) {
            case"A":
                objectiveRequirements.put(ObjectiveGroup.BASIC, 4);
                objectiveRequirements.put(ObjectiveGroup.CORE, 4);
                objectiveRequirements.put(ObjectiveGroup.EXTRA, 3);
                course.gradeBreaks.put("A", objectiveRequirements);
            case"A-":
                objectiveRequirements.put(ObjectiveGroup.BASIC, 4);
                objectiveRequirements.put(ObjectiveGroup.CORE, 3);
                objectiveRequirements.put(ObjectiveGroup.EXTRA, 2);
                course.gradeBreaks.put("A-", objectiveRequirements);
            case"B+":
                objectiveRequirements.put(ObjectiveGroup.BASIC, 3);
                objectiveRequirements.put(ObjectiveGroup.CORE, 3);
                objectiveRequirements.put(ObjectiveGroup.EXTRA, 2);
                course.gradeBreaks.put("B+", objectiveRequirements);
            case"B":
                objectiveRequirements.put(ObjectiveGroup.BASIC, 3);
                objectiveRequirements.put(ObjectiveGroup.CORE, 3);
                objectiveRequirements.put(ObjectiveGroup.EXTRA, 1);
                course.gradeBreaks.put("B", objectiveRequirements);
            case"B-":
                objectiveRequirements.put(ObjectiveGroup.BASIC, 3);
                objectiveRequirements.put(ObjectiveGroup.CORE, 2);
                objectiveRequirements.put(ObjectiveGroup.EXTRA, 1);
                course.gradeBreaks.put("B-", objectiveRequirements);
            case"C+":
                objectiveRequirements.put(ObjectiveGroup.BASIC, 3);
                objectiveRequirements.put(ObjectiveGroup.CORE, 2);
                objectiveRequirements.put(ObjectiveGroup.EXTRA, 0);
                course.gradeBreaks.put("C+", objectiveRequirements);
            case"C":
                objectiveRequirements.put(ObjectiveGroup.BASIC, 2);
                objectiveRequirements.put(ObjectiveGroup.CORE, 2);
                objectiveRequirements.put(ObjectiveGroup.EXTRA, 0);
                course.gradeBreaks.put("C", objectiveRequirements);
            case"C-":
                objectiveRequirements.put(ObjectiveGroup.BASIC, 2);
                objectiveRequirements.put(ObjectiveGroup.CORE, 1);
                objectiveRequirements.put(ObjectiveGroup.EXTRA, 0);
                course.gradeBreaks.put("C-", objectiveRequirements);
            case"D+":
                objectiveRequirements.put(ObjectiveGroup.BASIC, 1);
                objectiveRequirements.put(ObjectiveGroup.CORE, 1);
                objectiveRequirements.put(ObjectiveGroup.EXTRA, 0);
                course.gradeBreaks.put("D+", objectiveRequirements);
            case"D":
                objectiveRequirements.put(ObjectiveGroup.BASIC, 1);
                objectiveRequirements.put(ObjectiveGroup.CORE, 0);
                objectiveRequirements.put(ObjectiveGroup.EXTRA, 0);
                course.gradeBreaks.put("D", objectiveRequirements);
            case"F":
                objectiveRequirements.put(ObjectiveGroup.BASIC, 0);
                objectiveRequirements.put(ObjectiveGroup.CORE, 0);
                objectiveRequirements.put(ObjectiveGroup.EXTRA, 0);
                course.gradeBreaks.put("F", objectiveRequirements);
        }
        return objectiveRequirements;
    }
}
