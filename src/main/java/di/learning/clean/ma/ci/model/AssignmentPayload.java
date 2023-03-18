package di.learning.clean.ma.ci.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentPayload {
    private String title;
    private String description;
    private Long numberOfAdherent;
    private double reward;
    private Date duration;
    private Long processingCompanyId;
    private String pointOfDropId;
}
