package di.learning.clean.ma.ci.service.processingcompany_;

import di.learning.clean.ma.ci.entity.Assignment;
import di.learning.clean.ma.ci.entity.ProcessingCompany;

import java.util.List;

public interface ProcessingCompanyService {
    public List<ProcessingCompany> fetchAllProcessingCompanies();

    public ProcessingCompany fetchProcessingCompany(Long processingCompanyId);

    public String saveProcessingCompany(ProcessingCompany processingCompany, Long adminId);

    public String deleteProcessingCompany(Long processingCompanyId);

    public String updateProcessingCompany(Long processingCompanyId, ProcessingCompany processingCompany);

    public List<Assignment> fetchAllAssignment(Long processingCompanyId);
}
