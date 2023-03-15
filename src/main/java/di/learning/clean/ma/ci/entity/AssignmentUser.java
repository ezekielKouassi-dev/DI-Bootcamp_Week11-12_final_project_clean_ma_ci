package di.learning.clean.ma.ci.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "assignment_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class AssignmentUser {
    @EmbeddedId
    private AssignmentUserId id;

    @ManyToOne
    @MapsId("adherentId")
    private Adherent adherent;

    @ManyToOne
    @MapsId("assignmentId")
    private Assignment assignment;

    // FIXME : update the default value of state
    private String state = "in progress";
}
