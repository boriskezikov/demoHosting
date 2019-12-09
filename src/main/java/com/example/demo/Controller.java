package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/android")
public class Controller {

    private final DataSourceRoutingService service;

    @Autowired
    public Controller(DataSourceRoutingService service) {
        this.service = service;
    }

    @GetMapping
    public String printMyString(@RequestParam String letter) throws Exception {
        return service.getCityByFirstLetter(letter);
    }
}
