package com.jd.survey.zeus.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jd.survey.zeus.domain.ZeusQuestionAndAnswer;
import com.jd.survey.zeus.mapper.ZeusQuestionAndAnswerMapper;

@Transactional(readOnly = true)
@Service("ZeusQuestionAndAnswerService")
public class ZeusQuestionAndAnswerService {

    @Autowired
    private ZeusQuestionAndAnswerMapper<Serializable> zeusQuestionAndAnswerMapper;

    public List<ZeusQuestionAndAnswer> getQuestionAndAnswerList() {
        return zeusQuestionAndAnswerMapper.getQuestionAndAnswerList();
    }

}
