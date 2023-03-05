package di.learning.clean.ma.ci.controller;

import di.learning.clean.ma.ci.entity.Locality;
import di.learning.clean.ma.ci.service.locality.LocalityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/locality")
public class LocalityController {
    @Autowired
    private LocalityService localityService;
    @GetMapping()
    public List<Locality> fetchAllLocality() {
        return localityService.fetchAllLocality();
    }

    @GetMapping("/{id}")
    public Locality fetchLocalityById(@PathVariable("id") Long localityId) {
        return localityService.fetchLocalityById(localityId);
    }

    @PostMapping("/admin/{id}")
    public String saveLocality(@PathVariable("id") Long adminId, @RequestBody Locality locality) {
        return localityService.saveLocality(adminId, locality);
    }
}
