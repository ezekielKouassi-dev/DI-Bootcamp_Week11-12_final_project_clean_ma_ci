package di.learning.clean.ma.ci.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ranks")
public class Ranks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rankId;
    private String title;
    private String description;
    private Date createDat;
    private Date updateDat;
    @OneToMany(mappedBy = "ranks", cascade = CascadeType.ALL)
    private List<Adherent> adherentList;

    @ManyToOne
    private Admin admin;
}
