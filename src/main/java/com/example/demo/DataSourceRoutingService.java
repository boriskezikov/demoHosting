package com.example.demo;


import org.apache.commons.text.WordUtils;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;


@Service
@EnableCaching
public class DataSourceRoutingService {

    private static final String GET_CITY_BY_NAME = "select name from city where name like ?";
    private static final String VALIDATE_CITY = "select name from city where name = ?";
    private static final Random rand = new Random();


    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DataSourceRoutingService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @CacheEvict("city")
    public String getCityByFirstLetter(String letter) throws Exception {
        if (letter.length() != 1) {
            throw new Exception("Incorrect input");
        }
        List<String> response = jdbcTemplate.queryForList(GET_CITY_BY_NAME, String.class, letter + '%');
        return response.get(rand.nextInt(response.size()));
    }

    @CacheEvict("city")
    public Boolean validateCityExistence(String city){
        List<String> response = jdbcTemplate.queryForList(VALIDATE_CITY, String.class, WordUtils.capitalize(city));
        return !response.isEmpty();
    }
}
