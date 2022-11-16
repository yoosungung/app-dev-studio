package com.jd.survey.pub.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jd.survey.pub.domain.SurveyComment;
import com.jd.survey.pub.service.SurveyCommentService;

/**
 * 설문조사 댓글
 */
@RequestMapping({"/pub/survey/cmt"})
@Controller("surveyCommentController")
public class SurveyCommentController {
	
	//~ Instance fields ====================================================================
	
	@Autowired
	private SurveyCommentService surveyCommentService;
	
	//~ Methods ============================================================================
	
	/**
	 * 설문조사 댓글 목록
	 */
	@RequestMapping(method=RequestMethod.GET, produces="text/html")
	public String list(long surveyDefinitionId, Model model) {
		
		model.addAttribute("comments", surveyCommentService.getSurveyCommentsBySurveyDefinitionId(surveyDefinitionId));
		model.addAttribute("surveyDefinitionId", surveyDefinitionId); 
		return "pub/survey/cmt";
	}
	
	/**
	 * 설문조사 댓글 등록
	 */
	@Secured({"ROLE_SURVEY_PARTICIPANT", "ROLE_ADMIN", "ROLE_SURVEY_ADMIN"})
	@RequestMapping(method=RequestMethod.POST, produces="application/json; charset=utf-8")
	public @ResponseBody int add(SurveyComment surveyComment) {
		return surveyCommentService.addSurveyComment(surveyComment);
	}
	
	/**
	 * 설문조사 댓글 삭제
	 */
	@Secured({"ROLE_SURVEY_PARTICIPANT", "ROLE_ADMIN", "ROLE_SURVEY_ADMIN"})
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE, produces="application/json; charset=utf-8")
	public @ResponseBody int del(@PathVariable("id") long id) {
		return surveyCommentService.delSurveyComment(id);
	}

}
