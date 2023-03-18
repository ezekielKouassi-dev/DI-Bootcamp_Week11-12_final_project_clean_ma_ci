package di.learning.clean.ma.ci.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "locality")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Locality implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long localityId;
    private String name;
    @OneToMany(mappedBy = "locality", cascade = CascadeType.ALL)
    private List<PointOfDrop> pointOfDropList;

    @ManyToOne
    private Admin admin;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Locality locality)) return false;
        return Objects.equals(getLocalityId(), locality.getLocalityId()) && Objects.equals(getName(), locality.getName()) && Objects.equals(getPointOfDropList(), locality.getPointOfDropList()) && Objects.equals(getAdmin(), locality.getAdmin());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLocalityId(), getName(), getPointOfDropList(), getAdmin());
    }
}
