package di.learning.clean.ma.ci.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "assignment_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentUser {
    @EmbeddedId
    private AssignmentUserId id;

    @ManyToOne
    @MapsId("userId")
    private User user;

    @ManyToOne
    @MapsId("assignmentId")
    private Assignment assignment;
}
