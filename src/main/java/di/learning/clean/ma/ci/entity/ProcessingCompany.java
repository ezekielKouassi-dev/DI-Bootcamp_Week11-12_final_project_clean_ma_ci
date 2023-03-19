package di.learning.clean.ma.ci.entity;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "processing_company")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProcessingCompany extends User implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @JsonIdentityReference(alwaysAsId = true)
    private User user;
    @OneToMany(mappedBy = "processingCompany", cascade = CascadeType.ALL)
    @JsonIdentityReference(alwaysAsId = true)
    private List<Assignment> assignmentList;
    @ManyToOne
    private Admin admin;

    @OneToMany(mappedBy = "processingCompany")
    private List<Statistic> statistics;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProcessingCompany that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getUser(), that.getUser()) && Objects.equals(getAssignmentList(), that.getAssignmentList()) && Objects.equals(getAdmin(), that.getAdmin());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getUser(), getAssignmentList(), getAdmin());
    }
}
