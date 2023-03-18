package di.learning.clean.ma.ci.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "assignments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "assignmentId")
public class Assignment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assignmentId;
    private String title;
    private String description;
    private Long numberOfAdherent = 0L;
    private Long numberOfAcceptation = 0L;
    private double reward;
    private Date createDat = new Date();
    private Date updateDat;
    private Date duration;
    private boolean isCompleted;
    private boolean status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Assignment that)) return false;
        return Double.compare(that.getReward(), getReward()) == 0 && isCompleted() == that.isCompleted() && isStatus() == that.isStatus() && Objects.equals(getAssignmentId(), that.getAssignmentId()) && Objects.equals(getTitle(), that.getTitle()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getNumberOfAdherent(), that.getNumberOfAdherent()) && Objects.equals(getNumberOfAcceptation(), that.getNumberOfAcceptation()) && Objects.equals(getCreateDat(), that.getCreateDat()) && Objects.equals(getUpdateDat(), that.getUpdateDat()) && Objects.equals(getDuration(), that.getDuration()) && Objects.equals(getAssignmentUsers(), that.getAssignmentUsers()) && Objects.equals(getProcessingCompany(), that.getProcessingCompany()) && Objects.equals(getPointOfDrop(), that.getPointOfDrop());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAssignmentId(), getTitle(), getDescription(), getNumberOfAdherent(), getNumberOfAcceptation(), getReward(), getCreateDat(), getUpdateDat(), getDuration(), isCompleted(), isStatus(), getAssignmentUsers(), getProcessingCompany(), getPointOfDrop());
    }

    @OneToMany(mappedBy = "assignment")
    @JsonIdentityReference(alwaysAsId = true)
    private List<AssignmentUser> assignmentUsers;

    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
    private ProcessingCompany processingCompany;

    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
    private PointOfDrop pointOfDrop;
}
