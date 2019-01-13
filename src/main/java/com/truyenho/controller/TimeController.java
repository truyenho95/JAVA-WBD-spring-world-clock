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
        Date date = new Date();
        TimeZone timezoneByLocal = TimeZone.getDefault();
        TimeZone timezoneBySpecifiedCity = TimeZone.getTimeZone(city);
        long currentTimeInSpecifiedCity = date.getTime() + (timezoneBySpecifiedCity.getRawOffset() - timezoneByLocal.getRawOffset());
        date.setTime(currentTimeInSpecifiedCity);

        model.addAttribute("city", city);
        model.addAttribute("date", date);
        return "worldclock";
    }
}