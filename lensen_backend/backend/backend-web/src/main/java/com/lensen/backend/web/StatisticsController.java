package com.lensen.backend.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/static")
public class StatisticsController {
    /**
     * 欢迎页的初期化
     */
    @GetMapping("/init")
    public ModelAndView init() {
        return new ModelAndView("static/data");
    }
}
