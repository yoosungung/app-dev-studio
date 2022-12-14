package kr.ac.jj.survey.application.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.ac.jj.shared.config.props.SharedConfigProperties;
import kr.ac.jj.survey.application.main.service.MainServiceImpl;

/**
 * 메인 Controller
 */
@Controller
public class MainController {

    private @Autowired SharedConfigProperties sharedConfig;
    private @Autowired MainServiceImpl mainService;

    @GetMapping
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("defaultLocale", sharedConfig.getDefaultLocale());
        mav.setViewName("tiles2-main:/main/MainView");

        return mav;
    }

}
