package di.learning.clean.ma.ci.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="adherents")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Adherent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adherentId;
    private String lastName;
    private String firstName;
    private String email;
    private String phone;
    private String username;
    private String password;
    private String role;
    private Date createDat;
    private Date updateDat;
    private boolean status;
    @OneToMany(mappedBy = "adherent", cascade = CascadeType.ALL)
    private List<AdherentAssignment> adherentAssignments;
}
