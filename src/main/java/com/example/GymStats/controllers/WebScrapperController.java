package com.example.GymStats.controllers;

import com.example.GymStats.services.WebScrapperService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebScrapperController {

    @Autowired
    private WebScrapperService webScrapperService;

    @GetMapping("/scrape/files")
    public String scrapePDFs() {
        webScrapperService.scrape();
        return "PDF scraping initiated. Check the downloads folder!";
    }

}
