package com.jd.survey.pub.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jd.survey.pub.mapper.SurveySecUserDefinitionMapper;

@Transactional(readOnly = true)
@Service("SurveySecUserDefinitionService")
public class SurveySecUserDefinitionService {

    @Autowired
    private SurveySecUserDefinitionMapper<Serializable> secUserSurveyDefinitionMapper;

}
