package di.learning.clean.ma.ci.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "point_off_drop")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PointOfDrop {
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
}
