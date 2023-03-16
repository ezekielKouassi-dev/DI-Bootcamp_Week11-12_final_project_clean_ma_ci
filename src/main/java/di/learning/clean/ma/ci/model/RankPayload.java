package di.learning.clean.ma.ci.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RankPayload {
    private long id;

    private String name;

    private String description;

    private Long adminId;
}
