package domain.company.project.module.solver.strategy;

import ai.timefold.solver.core.api.score.buildin.hardsoft.HardSoftScore;
import domain.company.project.module.solver.UserConstraint;

public interface ConstraintStrategy {
    HardSoftScore applyWeight(UserConstraint constraint);
}
