package com.jd.survey.pub.mapper;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jd.survey.pub.domain.QuestionOption;

/**
 * 질문옵션
 */
public interface QuestionOptionMapper<T extends Serializable> {

    /**
     * 질문옵션 목록
     */
    List<QuestionOption> getQuestionOptionList(@Param("questionId") String questionId);

}
