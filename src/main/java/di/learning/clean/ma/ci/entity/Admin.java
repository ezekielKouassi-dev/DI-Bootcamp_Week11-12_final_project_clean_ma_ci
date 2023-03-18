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
public class Admin extends User{

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    @JsonIdentityReference(alwaysAsId = true)
    private List<Locality> localityList;

    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    private List<Ranks> ranksList;

    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    private List<PointOfDrop> pointOfDropList;

    @OneToMany(mappedBy = "admin")
    private List<ProcessingCompany> processingCompanylist;
}
