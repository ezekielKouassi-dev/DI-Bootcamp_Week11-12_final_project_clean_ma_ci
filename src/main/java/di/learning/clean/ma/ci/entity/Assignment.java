package di.learning.clean.ma.ci.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "assignments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assignmentId;
    private String title;
    private String description;
    private Long numberOfAdherent;
    private double reward;
    private Date createDat;
    private Date updateDat;
    private Date duration;
    private boolean isCompleted;
    private boolean status;
    @OneToMany(mappedBy = "assignment")
    private List<AdherentAssignment> adherentAssignments;
}
