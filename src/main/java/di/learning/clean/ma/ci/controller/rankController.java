package di.learning.clean.ma.ci.controller;

import di.learning.clean.ma.ci.model.RankPayload;
import di.learning.clean.ma.ci.service.rank_.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ranks")
@CrossOrigin(origins = "http://localhost:4200/")
public class rankController {

    @Autowired
    private RankService rankService;

    @GetMapping()
    public String fetchAllRanks() {
        return rankService.fetchAllRanks();
    }

    @GetMapping("/{id}")
    public String fetchRankById(@PathVariable("id") Long rankId) {
        return rankService.fetchRankById(rankId);
    }

    @PostMapping()
    public String saveRank(@RequestBody RankPayload rank) {
        return rankService.saveRank(rank);
    }

    @PutMapping("/{id}")
    public String updateRank(@PathVariable("id") Long rankId, @RequestBody RankPayload rankPayload) {
        return rankService.updateRank(rankId, rankPayload);
    }

    @DeleteMapping("/{id}")
    public String deleteRank(@PathVariable("id") Long rankId) {
        return rankService.deleteRank(rankId);
    }
}
