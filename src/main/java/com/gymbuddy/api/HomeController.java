package com.gymbuddy.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController
{
    @GetMapping("/")
    public String homepage()
    {
        return "Welcome to Gym Buddy";
    }
}
