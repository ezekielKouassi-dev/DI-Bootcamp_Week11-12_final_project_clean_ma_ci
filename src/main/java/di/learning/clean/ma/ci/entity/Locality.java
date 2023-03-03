package di.learning.clean.ma.ci.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "locality")
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
