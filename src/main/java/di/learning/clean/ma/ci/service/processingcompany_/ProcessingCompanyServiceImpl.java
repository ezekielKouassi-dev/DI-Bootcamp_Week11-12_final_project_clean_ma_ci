package di.learning.clean.ma.ci.service.processingcompany_;

import di.learning.clean.ma.ci.entity.Assignment;
import di.learning.clean.ma.ci.entity.ProcessingCompany;
import di.learning.clean.ma.ci.entity.User;
import di.learning.clean.ma.ci.repository.ProcessingCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProcessingCompanyServiceImpl implements ProcessingCompanyService{
    @Autowired
    private ProcessingCompanyRepository processingCompanyRepository;

    /**
     *
     * @return
     */
    @Override
    public List<ProcessingCompany> fetchAllProcessingCompanies() {
        return processingCompanyRepository.findAll();
    }

    /**
     * get company using id property
     *
     * @param processingCompanyId
     * @return
     */
    @Override
    public ProcessingCompany fetchProcessingCompany(Long processingCompanyId) {
        if(!processingCompanyRepository.findById(processingCompanyId).isPresent()) {
            // TODO : throw exception not found
            return null;
        }
        return processingCompanyRepository.findById(processingCompanyId).get();
    }

    /**
     *
     * @param processingCompany
     * @return
     */
    @Override
    public String saveProcessingCompany(ProcessingCompany processingCompany) {
        processingCompanyRepository.save(processingCompany);
        return "successfully save processing company";
    }

    /**
     *
     * @param processingCompanyId
     * @return
     */
    @Override
    public String deleteProcessingCompany(Long processingCompanyId) {
        if(!processingCompanyRepository.findById(processingCompanyId).isPresent()) {
            return null;
        }
        processingCompanyRepository.delete(processingCompanyRepository.findById(processingCompanyId).get());
        return "successfully delete processing Company";
    }

    /**
     *
     * @param processingCompanyId
     * @return
     */
    @Override
    public String updateProcessingCompany(Long processingCompanyId, ProcessingCompany processingCompany) {
        ProcessingCompany prc = processingCompanyRepository.findById(processingCompanyId).get();

        if(Objects.nonNull(prc.getName()) &&
                !"".equalsIgnoreCase(prc.getName())
        ) {
            prc.setName(processingCompany.getName());
        }

        if(Objects.nonNull(prc.getEmail()) &&
                !"".equalsIgnoreCase(prc.getEmail())
        ) {
            prc.setEmail(processingCompany.getEmail());
        }

        if(Objects.nonNull(prc.getPhone()) &&
                !"".equalsIgnoreCase(prc.getPhone())
        ) {
            prc.setPhone(processingCompany.getPhone());
        }

        // TODO : add all condition for update

        processingCompanyRepository.save(prc);
        return "successfully update processing company";
    }

    /**
     *
     * @param processingCompanyId
     * @return
     */
    @Override
    public List<Assignment> fetchAllAssignment(Long processingCompanyId) {
        Optional<ProcessingCompany> processingCompany = processingCompanyRepository.findById(processingCompanyId);

        if(!processingCompany.isPresent()) {
            return null;
        }
        return processingCompany.get().getAssignmentList();
    }
}
