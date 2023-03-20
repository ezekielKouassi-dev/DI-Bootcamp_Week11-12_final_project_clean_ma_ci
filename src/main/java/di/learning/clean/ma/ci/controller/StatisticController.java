package di.learning.clean.ma.ci.controller;

import di.learning.clean.ma.ci.entity.AssignmentUser;
import di.learning.clean.ma.ci.service.assignmentUser_.AssignmentUserService;
import di.learning.clean.ma.ci.service.statistic_.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/statistics")
@CrossOrigin(origins = "http://localhost:4200/")
public class StatisticController {

    @Autowired
    private StatisticService statisticService;

    @Autowired
    private AssignmentUserService assignmentUserService;


    /*
    * 1- found list of all assignment that adherent has been completed by processingCompany and count it
    * then put it into statistics table
    * 2- request in userRepository for get all user assignment who leave any assignment
    * */

}
