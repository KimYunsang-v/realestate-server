package kr.ac.skuniv.realestate.controller;

import kr.ac.skuniv.realestate.repository.ConditionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "realestate/condition/*")
public class conditionController {

    @Autowired
    private ConditionRepository conditionRepository;

    @GetMapping("/{region}")
    public String onlyRegion(@PathVariable String region){
        System.out.println(region);
        return conditionRepository.getPriceSum() + "";
    }

    @GetMapping("/{region}/{term}")
    public String termRegion(@PathVariable String region, @PathVariable String term){
        System.out.println(region+term);
        return region + term;
    }
}
