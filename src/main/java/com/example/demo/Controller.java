package com.example.demo;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/android")
public class Controller {

    private final DataSourceRoutingService service;

    @Autowired
    public Controller(DataSourceRoutingService service) {
        this.service = service;
    }

    @GetMapping("/get_city")
    public String getNewCity(@RequestParam String letter) throws Exception {
        return service.getCityByFirstLetter(letter);
    }

    @GetMapping("/validate_city")
    public Boolean checkCity(@RequestParam String city){
        return service.validateCityExistence(city);
    }

    @GetMapping("/health")
    public Boolean getStatus(){
        return true;
    }
}
