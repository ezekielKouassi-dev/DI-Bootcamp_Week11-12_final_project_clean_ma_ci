package di.learning.clean.ma.ci.controller;

import di.learning.clean.ma.ci.entity.PointOfDrop;
import di.learning.clean.ma.ci.service.pointofdrop_.PointOfDropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pointOfDrop")
@CrossOrigin(origins = "http://localhost:4200/")
public class PointOfDropController {
    @Autowired
    private PointOfDropService pointOfDropService;
    @GetMapping()
    public String fetchAllPointOfDrop() {
        return pointOfDropService.fetchAllPointOfDrop();
    }
    @PostMapping("/admin/{id}")
    public String savePointOfDrop(@RequestBody PointOfDrop pointOfDrop, @PathVariable("id") Long adminId) {
        return pointOfDropService.savePointOfDrop(pointOfDrop,adminId);
    }
}
