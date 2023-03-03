package di.learning.clean.ma.ci.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;
    private String lastName;
    private String firstName;
    private String password;
    private String email;
    private String phone;
    private Date createDat;
    private Date updateDat;
    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    private List<Locality> localityList;

    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    private List<Ranks> ranksList;

    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    private List<PointOfDrop> pointOfDropList;
}