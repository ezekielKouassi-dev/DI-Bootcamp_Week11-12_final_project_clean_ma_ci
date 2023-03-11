package di.learning.clean.ma.ci.controller;

import di.learning.clean.ma.ci.entity.Admin;
import di.learning.clean.ma.ci.entity.Locality;
import di.learning.clean.ma.ci.entity.PointOfDrop;
import di.learning.clean.ma.ci.entity.Ranks;
import di.learning.clean.ma.ci.service.admin_.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@CrossOrigin(origins = "http://localhost:4200/")
public class AdminController {
    @Autowired
    private AdminService adminService;

    /**
     *
     * @param adminId
     * @return
     */
    @GetMapping("/{id}")
    public Admin fetchAdminById(@PathVariable("id") Long adminId) {
        return adminService.fetchAdminById(adminId);
    }

    /**
     *
     * @param adminId
     * @return
     */
    @GetMapping("/{id}/locality")
    public List<Locality> fetchAllLocality(@PathVariable("id") Long adminId) {
        return adminService.fetchAllLocality(adminId);
    }

    /**
     *
     * @param ranksId
     * @return
     */
    @GetMapping("/{id}/ranks")
    public List<Ranks> fetchAllRank(@PathVariable("id") Long ranksId) {
        return adminService.fetchAllRank(ranksId);
    }

    /**
     *
     * @param pointOfDropId
     * @return
     */
    @GetMapping("/{id}/pointOfDrop")
    public List<PointOfDrop> fetchAllDropOfPoint(@PathVariable("id") Long pointOfDropId) {
        return adminService.fetchAllDropOfPoint(pointOfDropId);
    }

    @PostMapping()
    public String saveAdmin(@RequestBody Admin admin) {
        return adminService.saveAdmin(admin);
    }
}
