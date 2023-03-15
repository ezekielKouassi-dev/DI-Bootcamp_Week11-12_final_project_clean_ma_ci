package di.learning.clean.ma.ci.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentUserId implements Serializable {
    private Long adherentId;
    private Long assignmentId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AssignmentUserId that)) return false;
        return getAdherentId().equals(that.getAdherentId()) && getAssignmentId().equals(that.getAssignmentId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAdherentId(), getAssignmentId());
    }




}
