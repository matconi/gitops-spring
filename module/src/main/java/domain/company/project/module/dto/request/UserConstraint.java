package domain.company.project.module.dto.request;

import lombok.Data;


@Data
public class UserConstraint {
    private String name;
    private Boolean enabled;
    private Integer weight;
}
