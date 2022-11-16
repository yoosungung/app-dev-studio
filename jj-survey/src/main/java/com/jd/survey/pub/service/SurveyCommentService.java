package com.jd.survey.pub.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jd.survey.com.helper.UserHelper;
import com.jd.survey.pub.domain.SurveyComment;
import com.jd.survey.pub.mapper.SurveyCommentMapper;

@Transactional(readOnly = true)
@Service("SurveyCommentService")
public class SurveyCommentService {

    // ~ Static fields/initializers
    // =========================================================

    // ~ Default Instance fields
    // ============================================================

    @Autowired
    private SurveyCommentMapper<Serializable> surveyCommentMapper;

    // ~ Methods
    // ============================================================================

    /**
     * 설문조사 댓글 조회
     */
    public SurveyComment getSurveyComment(long id) {
        return surveyCommentMapper.getSurveyComment(id);
    }

    /**
     * 설문조사 댓글 목록 조회
     */
    public List<SurveyComment> getSurveyCommentsBySurveyDefinitionId(long surveyDefinitionId) {
        return surveyCommentMapper.getSurveyCommentsBySurveyDefinitionId(surveyDefinitionId);
    }

    /**
     * 설문조사 댓글 등록
     */
    @Transactional(readOnly = false)
    public int addSurveyComment(SurveyComment surveyComment) {
        System.out.println("test1");
        if (UserHelper.isUser()) {
            surveyComment.setUserId(UserHelper.getUserId());
            surveyCommentMapper.addSurveyComment(surveyComment);
            System.out.println("test2");
            return 1;
        } else {
            System.out.println("test3");
            return -1;
        }
    }

    /**
     * 설문조사 댓글 수정
     */
    @Transactional(readOnly = false)
    public int modSurveyComment(SurveyComment surveyComment) {
        SurveyComment oldSurveyComment = surveyCommentMapper.getSurveyComment(surveyComment.getId());
        if (oldSurveyComment == null) {
            return 0;
        } else if (!UserHelper.isEqualUser(oldSurveyComment.getUserId())) {
            return -1;
        }
        return surveyCommentMapper.modSurveyComment(surveyComment);
    }

    /**
     * 설문조사 댓글 삭제
     */
    @Transactional(readOnly = false)
    public int delSurveyComment(long id) {
        SurveyComment oldSurveyComment = surveyCommentMapper.getSurveyComment(id);
        if (oldSurveyComment == null) {
            return 0;
        } else if (!UserHelper.isEqualUser(oldSurveyComment.getUserId())) {
            return -1;
        } else
            return surveyCommentMapper.delSurveyComment(id);
    }

}
