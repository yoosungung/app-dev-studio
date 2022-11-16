package kr.ac.jj.dashboard.application.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.jj.dashboard.application.main.mapper.MainMapper;

/**
 * 메인 Service
 */
@Service
public class MainServiceImpl {

    private @Autowired MainMapper mainMapper;

}
