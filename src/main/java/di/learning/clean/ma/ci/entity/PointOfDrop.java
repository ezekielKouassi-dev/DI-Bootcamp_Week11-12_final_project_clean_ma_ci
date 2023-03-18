package di.learning.clean.ma.ci.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "point_off_drop")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PointOfDrop implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pointOfDropId;
    private String name;
    private Date createDat;
    private Date updateDat;
    private boolean status;
    @OneToMany(mappedBy = "pointOfDrop", cascade = CascadeType.ALL)
    List<Assignment> assignmentList;

    @ManyToOne
    private Locality locality;

    @ManyToOne
    private Admin admin;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PointOfDrop that)) return false;
        return isStatus() == that.isStatus() && Objects.equals(getPointOfDropId(), that.getPointOfDropId()) && Objects.equals(getName(), that.getName()) && Objects.equals(getCreateDat(), that.getCreateDat()) && Objects.equals(getUpdateDat(), that.getUpdateDat()) && Objects.equals(getAssignmentList(), that.getAssignmentList()) && Objects.equals(getLocality(), that.getLocality()) && Objects.equals(getAdmin(), that.getAdmin());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPointOfDropId(), getName(), getCreateDat(), getUpdateDat(), isStatus(), getAssignmentList(), getLocality(), getAdmin());
    }
}
