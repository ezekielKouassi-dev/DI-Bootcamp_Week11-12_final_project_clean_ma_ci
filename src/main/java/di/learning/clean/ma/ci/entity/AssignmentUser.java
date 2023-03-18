package di.learning.clean.ma.ci.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "assignment_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class AssignmentUser implements Serializable {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AssignmentUser that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getAdherent(), that.getAdherent()) && Objects.equals(getAssignment(), that.getAssignment()) && Objects.equals(getState(), that.getState());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAdherent(), getAssignment(), getState());
    }
}
