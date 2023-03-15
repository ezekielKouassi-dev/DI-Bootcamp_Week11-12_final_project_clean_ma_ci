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
public class ProcessingCompany extends User{
    @Column(name = "processing_company_id")
    private String processingCompanyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;
    @OneToMany(mappedBy = "processingCompany", cascade = CascadeType.ALL)
    @JsonIdentityReference(alwaysAsId = true)
    private List<Assignment> assignmentList;
    @ManyToOne
    private Admin admin;
}
