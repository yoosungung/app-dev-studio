package kr.ac.jj.dashboard.application.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.ac.jj.dashboard.application.main.service.MainServiceImpl;
import kr.ac.jj.shared.config.props.SharedConfigProperties;

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
        mav.setViewName("tiles-main:/main/MainView");

        return mav;
    }

}
