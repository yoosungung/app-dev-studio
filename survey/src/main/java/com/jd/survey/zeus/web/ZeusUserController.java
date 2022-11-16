package com.jd.survey.zeus.web;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jd.survey.domain.security.User;
import com.jd.survey.service.security.UserService;
import com.jd.survey.service.settings.SurveySettingsService;
import com.jd.survey.zeus.service.ZeusUserDetailsService;
import com.jd.survey.zeus.service.ZeusUserService;
import com.jd.survey.zeus.web.cmd.ZeusUserCmd;


@RequestMapping({"/mng/users"})
@Controller
public class ZeusUserController {
	
	//~ Static fields/initializers =========================================================

	//private static final Log log = LogFactory.getLog(ZeusUserController.class);	

	//~ Instance fields ====================================================================
	
	@Autowired private ZeusUserService zeusUserService;
	
	@Autowired	private SurveySettingsService surveySettingsService;
	
	@Autowired	private UserService userService;
	
	//~ Methods ============================================================================
	
	/**
	 * 사용자 전환
	 */
	@RequestMapping(value="/{id}", params="change", method=RequestMethod.GET, produces="text/html")
	public String change(@PathVariable("id") String id) {
		
		SecurityContext context = SecurityContextHolder.getContext();
		User user = (User)(zeusUserService.loadUserByUsername(id));
		
		if(user != null) {
			PasswordEncoder encoder = new ShaPasswordEncoder(256);
			context.setAuthentication(new UsernamePasswordAuthenticationToken(user, encoder.encodePassword("1", user.getSalt()), user.getAuthorities()));
		}
		return "redirect:/pub/main";
	}
	
	/**
	 * 사용자 목록
	 */
	@RequestMapping(method=RequestMethod.GET, produces="text/html")
	public String users(ZeusUserCmd cmd, Principal principal, Model model) {
		
		User user = userService.user_findByLogin(principal.getName());
		
		//설문 선택시
		if(cmd.getSurveyId() != 0) {
			model.addAttribute("cmd", zeusUserService.searchSurveyUsers(cmd));
		}
		else {
			model.addAttribute("cmd", zeusUserService.searchUsers(cmd));
		}
		model.addAttribute("surveyDefinitions", surveySettingsService.surveyDefinition_findAllInternal(user));
		return "mng/users";
	}
	
	/**
	 * 사용자 등록
	 */
	@RequestMapping(method=RequestMethod.POST, produces="application/json; charset=utf-8")
	public @ResponseBody Map<String, String> addUser(String surveyId, String userId) {
		
		Map<String, String> map = new HashMap<String, String>();
		
		if(userId.equals("all")) {
			map.put("count", zeusUserService.addAllUserServey(surveyId));
		}
		else {
			map = zeusUserService.addUserServey(userId, surveyId);
			if(map.get("fail") != null) {
				map.put("fail", map.get("fail"));   
		    }
		}
		return map;
	}
	
	/**
	 * 사용자 삭제
	 */
	@RequestMapping(method=RequestMethod.DELETE, produces="application/json; charset=utf-8")
	public @ResponseBody int delUser(String surveyId, String userId) {
		int result = 0;
		
		if(userId.equals("all")) {
			result = userService.delUserSurveyDefinitionsBySurveyDefinitionId(Long.valueOf(surveyId).longValue());
		}
		else {
			User user = userService.user_findByLogin(userId);
			userService.delUserSurveyDefinitionBySurveyDefinitionIdAndUserId(Long.valueOf(surveyId).longValue(), user.getId());
		}
		return result;
	}
		
}
