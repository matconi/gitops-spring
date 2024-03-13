package domain.company.project.module.solver;

import ai.timefold.solver.core.api.score.buildin.hardsoft.HardSoftScore;
import lombok.Data;


@Data
public class UserConstraint {
    private String name;
    private Boolean enabled;
    private Integer weight;

    public HardSoftScore applyWeight() {
        if (enabled) {
            switch (this.getWeight()) {
                case 1 -> {
                    return HardSoftScore.ONE_SOFT;
                }
                case 2 -> {
                    return HardSoftScore.ONE_HARD;
                }
                default -> {
                    return HardSoftScore.ZERO;
                }
            }
        }
        return HardSoftScore.ZERO;
    }
}
