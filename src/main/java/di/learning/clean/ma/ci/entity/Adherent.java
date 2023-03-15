package di.learning.clean.ma.ci.entity;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
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
public class Adherent extends User{
    @Column(name = "adherent_id")
    private String adherentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @OneToMany(mappedBy = "adherent")
    @JsonIdentityReference(alwaysAsId = true)
    private List<AssignmentUser> assignmentUsers;
    @ManyToOne
    private Ranks ranks;
}
