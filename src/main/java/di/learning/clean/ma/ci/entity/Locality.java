package di.learning.clean.ma.ci.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "locality")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Locality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long localityId;
    private String name;
    @OneToMany(mappedBy = "locality", cascade = CascadeType.ALL)
    private List<PointOfDrop> pointOfDropList;

    @ManyToOne
    private Admin admin;
}
