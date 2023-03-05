package di.learning.clean.ma.ci.entity;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "processing_company")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcessingCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long processingCompanyId;
    private String name;
    private String phone;
    private String email;
    private Date createDat = new Date();
    private Date updateDat;
    @OneToMany(mappedBy = "processingCompany", cascade = CascadeType.ALL)
    @JsonIdentityReference(alwaysAsId = true)
    private List<Assignment> assignmentList;
}
