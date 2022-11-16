package com.jd.survey.pub.mapper;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jd.survey.pub.domain.Question;

/**
 * 질문
 */
public interface QuestionMapper<T extends Serializable> {

    /**
     * 질문 목록
     */
    List<Question> getQuestionList(@Param("surveyDefinitionPageId") String surveyDefinitionPageId);

}
