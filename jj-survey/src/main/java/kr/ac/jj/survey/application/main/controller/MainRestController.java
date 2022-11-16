package kr.ac.jj.survey.application.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.jj.survey.application.main.service.MainServiceImpl;

/**
 * 메인 Controller
 */
@RestController
@RequestMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
public class MainRestController {

    private @Autowired MainServiceImpl mainService;

}
