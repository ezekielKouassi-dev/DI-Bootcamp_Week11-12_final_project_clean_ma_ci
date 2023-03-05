package di.learning.clean.ma.ci.controller;

import di.learning.clean.ma.ci.entity.Assignment;
import di.learning.clean.ma.ci.entity.ProcessingCompany;
import di.learning.clean.ma.ci.service.processingcompany_.ProcessingCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/processingCompany")
public class ProcessingCompanyController {
    @Autowired
    private ProcessingCompanyService processingCompanyService;

    /**
     *
     * @return
     */
    @GetMapping()
    public List<ProcessingCompany> fetchAllProcessingCompanies() {
        return processingCompanyService.fetchAllProcessingCompanies();
    }

    /**
     *
     * @param processingCompanyId
     * @return
     */
    @GetMapping("/{id}")
    public ProcessingCompany fetchProcessingCompany(@PathVariable("id") Long processingCompanyId) {
        return processingCompanyService.fetchProcessingCompany(processingCompanyId);
    }

    @GetMapping("/{id}/assignments")
    public List<Assignment> fetchAllAssignment(@PathVariable("id") Long processingCompanyId) {
        return processingCompanyService.fetchAllAssignment(processingCompanyId);
    }

    /**
     *
     * @param processingCompany
     * @return
     */
    @PostMapping()
    public String saveProcessingCompany(@RequestBody ProcessingCompany processingCompany) {
        return processingCompanyService.saveProcessingCompany(processingCompany);
    }

    /**
     *
     * @param processingCompanyId
     * @param processingCompany
     * @return
     */
    @PutMapping("/{id}")
    public String updateProcessingCompany(@PathVariable("id") Long processingCompanyId, @RequestBody ProcessingCompany processingCompany) {
        return processingCompanyService.updateProcessingCompany(processingCompanyId, processingCompany);
    }

    /**
     *
     * @param processingCompanyId
     * @return
     */
    @DeleteMapping("/{id}")
    public String deleteProcessingCompany(@PathVariable("id") Long processingCompanyId) {
        return processingCompanyService.deleteProcessingCompany(processingCompanyId);
    }
}
