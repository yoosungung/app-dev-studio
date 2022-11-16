package kr.ac.jj.survey.application.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.ac.jj.survey.application.main.service.MainServiceImpl;
import kr.ac.jj.survey.config.props.ConfigProperties;

/**
 * 메인 Controller
 */
@Controller
public class MainController {
    private @Autowired ConfigProperties config;
    private @Autowired MainServiceImpl mainService;

    @GetMapping
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("defaultLocale", config.getDefaultLocale());
        mav.setViewName("index");

        return mav;
    }
}
