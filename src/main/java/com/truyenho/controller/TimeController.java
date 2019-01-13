package com.truyenho.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.TimeZone;

@Controller
public class TimeController {
	
    @RequestMapping("/worldclock")
    public String getTimeByTimezone(ModelMap model, @RequestParam(name = "city", required = false, defaultValue = "Asia/Ho_Chi_Minh") String city) {
        Date date = new Date(); // Get current time at local
        TimeZone local = TimeZone.getDefault(); // Get timezone by the local
        TimeZone locale = TimeZone.getTimeZone(city); // Get timezone by the specified city

        long locale_time = date.getTime() + (locale.getRawOffset() - local.getRawOffset()); // Calculate the current time in the specified city
        date.setTime(locale_time); // Reset the date by locale_time
        
        // Set the data sent to the view
        model.addAttribute("city", city);
        model.addAttribute("date", date);
        return "worldclock";
    }
}