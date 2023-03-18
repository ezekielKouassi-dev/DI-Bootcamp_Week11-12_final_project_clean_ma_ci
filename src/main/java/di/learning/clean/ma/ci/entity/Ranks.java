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
@Table(name = "ranks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ranks implements Serializable {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ranks ranks)) return false;
        return Objects.equals(getRankId(), ranks.getRankId()) && Objects.equals(getTitle(), ranks.getTitle()) && Objects.equals(getDescription(), ranks.getDescription()) && Objects.equals(getCreateDat(), ranks.getCreateDat()) && Objects.equals(getUpdateDat(), ranks.getUpdateDat()) && Objects.equals(getStatus(), ranks.getStatus()) && Objects.equals(getAdherentList(), ranks.getAdherentList()) && Objects.equals(getAdmin(), ranks.getAdmin());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRankId(), getTitle(), getDescription(), getCreateDat(), getUpdateDat(), getStatus(), getAdherentList(), getAdmin());
    }
}
