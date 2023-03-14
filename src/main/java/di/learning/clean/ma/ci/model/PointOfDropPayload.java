package di.learning.clean.ma.ci.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PointOfDropPayload {
    private String name;
    private Long locality;

}
