package di.learning.clean.ma.ci.entity;

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
    private Date createDat;
    private Date updateDat;
    @OneToMany(mappedBy = "processingCompany", cascade = CascadeType.ALL)
    private List<Assignment> assignmentList;
}
