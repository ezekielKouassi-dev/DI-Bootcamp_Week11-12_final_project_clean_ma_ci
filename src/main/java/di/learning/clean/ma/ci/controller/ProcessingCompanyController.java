package di.learning.clean.ma.ci.controller;

import di.learning.clean.ma.ci.entity.Assignment;
import di.learning.clean.ma.ci.entity.ProcessingCompany;
import di.learning.clean.ma.ci.model.ProcessingCompanyPayload;
import di.learning.clean.ma.ci.service.processingcompany_.ProcessingCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/processingCompany")
@CrossOrigin(origins = "http://localhost:4200/")
public class ProcessingCompanyController {
    @Autowired
    private ProcessingCompanyService processingCompanyService;

    /**
     *
     * @return
     */
    @GetMapping()
    public String fetchAllProcessingCompanies() {
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
    public String fetchAllAssignment(@PathVariable("id") Long processingCompanyId) {
        return processingCompanyService.fetchAllAssignment(processingCompanyId);
    }

    /**
     *
     * @param processingCompanyPayload
     * @return
     */
    @PostMapping()
    public String saveProcessingCompany(@RequestBody ProcessingCompanyPayload processingCompanyPayload) {
        return processingCompanyService.saveProcessingCompany(processingCompanyPayload);
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
