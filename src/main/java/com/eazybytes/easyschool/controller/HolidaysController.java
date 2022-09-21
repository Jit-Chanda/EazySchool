package com.eazybytes.easyschool.controller;

import com.eazybytes.easyschool.model.Holiday;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HolidaysController {

    //if we want to take the values from frontend through request param
//    @GetMapping("/holidays")
//    public String displayHolidays(@RequestParam(required = false) boolean festival,
//                                  @RequestParam(required = false) boolean federal,
//                                  Model model) {
//        model.addAttribute("festival", festival);
//        model.addAttribute("federal", federal);
//        List<Holiday> holidays = Arrays.asList(
//                new Holiday("JAN 1", "NEW YEAR'S DAY", Holiday.Type.FESTIVAL),
//                new Holiday("OCT 31", "HALLOWEEN", Holiday.Type.FESTIVAL),
//                new Holiday("SEP 5", "LABOUR'S DAY", Holiday.Type.FEDERAL),
//                new Holiday("DEC 1", "DECEMBER'S DAY", Holiday.Type.FESTIVAL),
//                new Holiday("DEC 25", "CHRISTMAS", Holiday.Type.FESTIVAL)
//        );
//
//        Holiday.Type[] types = Holiday.Type.values();
//        for(Holiday.Type type : types) {
//            model.addAttribute(type.toString(),
//                    (holidays.stream().filter(holiday -> holiday.getType().equals(type)).collect(Collectors.toList())));
//        }
//
//        return "holidays.html";
//    }

    //if we want to take the values from frontend through path variable
    @GetMapping("/holidays/{display}")
    public String displayHolidays(@PathVariable(required = false) String display,
                                  Model model) {
        if(null != display && display.equals("all")) {
            model.addAttribute("festival", true);
            model.addAttribute("federal", true);
        } else if(null != display && display.equals("federal")) {
            model.addAttribute("federal", true);
        } else if(null != display && display.equals("festival")) {
            model.addAttribute("festival", true);
        }
        List<Holiday> holidays = Arrays.asList(
                new Holiday("JAN 1", "NEW YEAR'S DAY", Holiday.Type.FESTIVAL),
                new Holiday("OCT 31", "HALLOWEEN", Holiday.Type.FESTIVAL),
                new Holiday("SEP 5", "LABOUR'S DAY", Holiday.Type.FEDERAL),
                new Holiday("DEC 1", "DECEMBER'S DAY", Holiday.Type.FESTIVAL),
                new Holiday("DEC 25", "CHRISTMAS", Holiday.Type.FESTIVAL)
        );

        Holiday.Type[] types = Holiday.Type.values();
        for(Holiday.Type type : types) {
            model.addAttribute(type.toString(),
                    (holidays.stream().filter(holiday -> holiday.getType().equals(type)).collect(Collectors.toList())));
        }

        return "holidays.html";
    }
}
