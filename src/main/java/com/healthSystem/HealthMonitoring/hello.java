package com.healthSystem.HealthMonitoring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class hello {


    @GetMapping("/siddhu")
    public String siddu(){
        return "siddhu loves keerthana❤️ ";
    }

    @GetMapping("/keerthana")
    public String keerthana(){
        return "keerthana loves siddhu❤️";
    }
}