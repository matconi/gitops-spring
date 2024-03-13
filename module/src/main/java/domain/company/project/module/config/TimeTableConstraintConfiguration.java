package domain.company.project.module.config;


import ai.timefold.solver.core.api.domain.constraintweight.ConstraintConfiguration;
import ai.timefold.solver.core.api.domain.constraintweight.ConstraintWeight;
import ai.timefold.solver.core.api.score.buildin.hardsoft.HardSoftScore;
import domain.company.project.module.solver.UserConstraint;
import lombok.Data;

import java.util.List;

@Data
@ConstraintConfiguration(constraintPackage = "domain.company.project.module.solver")
public class TimeTableConstraintConfiguration {
    public static final String ROOM_CONFLICT = "roomConflict";
    public static final String TEACHER_CONFLICT = "teacherConflict";
    public static final String STUDENT_GROUP_CONFLICT = "studentGroupConflict";
    public static final String TEACHER_AVAILABILITY_CONFLICT = "teacherAvailabilityConflict";
    public static final String TEACHER_ROOM_STABILITY = "teacherRoomStability";
    public static final String TEACHER_TIME_EFFICIENCY = "teacherTimeEfficiency";
    public static final String STUDENT_GROUP_VARIETY = "studentGroupVariety";

    @ConstraintWeight(ROOM_CONFLICT)
    private HardSoftScore roomConflict = HardSoftScore.ZERO; //hard
    @ConstraintWeight(TEACHER_CONFLICT)
    private HardSoftScore teacherConflict = HardSoftScore.ZERO; //hard
    @ConstraintWeight(STUDENT_GROUP_CONFLICT)
    private HardSoftScore studentGroupConflict = HardSoftScore.ZERO; //hard
    @ConstraintWeight(TEACHER_AVAILABILITY_CONFLICT)
    private HardSoftScore teacherAvailabilityConflict = HardSoftScore.ZERO; //hard
    @ConstraintWeight(TEACHER_ROOM_STABILITY)
    private HardSoftScore teacherRoomStability = HardSoftScore.ZERO; //soft
    @ConstraintWeight(TEACHER_TIME_EFFICIENCY)
    private HardSoftScore teacherTimeEfficiency = HardSoftScore.ZERO; //soft
    @ConstraintWeight(STUDENT_GROUP_VARIETY)
    private HardSoftScore studentGroupVariety = HardSoftScore.ZERO; //soft

    public TimeTableConstraintConfiguration(List<UserConstraint> constraints){
        constraints.forEach(constraint -> {
            switch (constraint.getName()) {
                case ROOM_CONFLICT -> this.setRoomConflict(constraint.applyWeight());
                case TEACHER_CONFLICT -> this.setTeacherConflict(constraint.applyWeight());
                case STUDENT_GROUP_CONFLICT ->
                        this.setStudentGroupConflict(constraint.applyWeight());
                case TEACHER_AVAILABILITY_CONFLICT ->
                        this.setTeacherAvailabilityConflict(constraint.applyWeight());
                case TEACHER_ROOM_STABILITY ->
                        this.setTeacherRoomStability(constraint.applyWeight());
                case TEACHER_TIME_EFFICIENCY ->
                        this.setTeacherTimeEfficiency(constraint.applyWeight());
                case STUDENT_GROUP_VARIETY -> this.setStudentGroupVariety(constraint.applyWeight());
                default -> {}
            }
        });
    }
}
