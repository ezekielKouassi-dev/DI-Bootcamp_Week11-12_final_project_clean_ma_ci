package di.learning.clean.ma.ci.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "adherent_assignment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdherentAssignment {
    @EmbeddedId
    private AdherentAssignmentId id;

    @ManyToOne
    @MapsId("adherentId")
    private Adherent adherent;

    @ManyToOne
    @MapsId("assignmentId")
    private Assignment assignment;
}
