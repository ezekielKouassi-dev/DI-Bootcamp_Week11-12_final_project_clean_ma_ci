package di.learning.clean.ma.ci.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "statistics")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Statistic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long numberOfCompletedAssignment = 0L;

    private Long numberOfLeaveAssignment = 0L;

    private Date statDat;

    private Date createDat;

    private Date updateDat;

    @ManyToOne
    private ProcessingCompany processingCompany;
}
