package kr.ac.jj.survey.application.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.jj.survey.application.main.mapper.MainMapper;

/**
 * 메인 Service
 */
@Service
public class MainServiceImpl {

    private @Autowired MainMapper mainMapper;

}
