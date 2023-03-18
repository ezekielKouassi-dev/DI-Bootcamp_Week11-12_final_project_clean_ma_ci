package di.learning.clean.ma.ci.service.processingcompany_;

import di.learning.clean.ma.ci.entity.Assignment;
import di.learning.clean.ma.ci.entity.ProcessingCompany;
import di.learning.clean.ma.ci.model.ProcessingCompanyPayload;

import java.util.List;

public interface ProcessingCompanyService {
    String fetchAllProcessingCompanies();

    public ProcessingCompany fetchProcessingCompany(Long processingCompanyId);

    public String saveProcessingCompany(ProcessingCompanyPayload processingCompanyPayload);

    public String deleteProcessingCompany(Long processingCompanyId);

    public String updateProcessingCompany(Long processingCompanyId, ProcessingCompany processingCompany);

    public String fetchAllAssignment(Long processingCompanyId);
}
