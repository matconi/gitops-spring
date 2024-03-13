package domain.company.project.module.solver.strategy;

import ai.timefold.solver.core.api.score.buildin.hardsoft.HardSoftScore;
import domain.company.project.module.solver.UserConstraint;

public class StudentGroupVarietyStrategy implements ConstraintStrategy {
    @Override
    public HardSoftScore applyWeight(UserConstraint constraint) {
        if (constraint.getWeight().equals(1)) {
            return HardSoftScore.ONE_SOFT;
        } else if (constraint.getWeight().equals(2)) {
            return HardSoftScore.ONE_HARD;
        }
        return HardSoftScore.ZERO;
    }
}
