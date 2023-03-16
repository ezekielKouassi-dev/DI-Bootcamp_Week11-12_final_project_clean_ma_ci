package di.learning.clean.ma.ci.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ranks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ranks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rankId;
    private String title;
    private String description;
    private Date createDat;
    private Date updateDat;
    private String status;
    @OneToMany(mappedBy = "ranks", cascade = CascadeType.ALL)
    private List<Adherent> adherentList;

    @ManyToOne
    private Admin admin;
}
