package com.jd.survey.zeus.mapper;

import java.io.Serializable;
import java.util.List;

import com.jd.survey.zeus.domain.ZeusQuestionAndAnswer;

import kr.ac.jj.survey.config.datasources.DataSourceMainConfig.MainSqlMapper;

@MainSqlMapper
public interface ZeusQuestionAndAnswerMapper<T extends Serializable> {

    List<ZeusQuestionAndAnswer> getQuestionAndAnswerList();

}
