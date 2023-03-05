package di.learning.clean.ma.ci.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "admin")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "adminId")
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
    @JsonIdentityReference(alwaysAsId = true)
    private List<Locality> localityList;

    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    private List<Ranks> ranksList;

    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    private List<PointOfDrop> pointOfDropList;
}
