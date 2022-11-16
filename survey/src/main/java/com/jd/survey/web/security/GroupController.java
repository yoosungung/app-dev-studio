  /*Copyright (C) 2014  JD Software, Inc.

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
  */
package com.jd.survey.web.security;

import java.io.UnsupportedEncodingException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

import com.jd.survey.domain.security.Group;
import com.jd.survey.domain.security.SecurityType;
import com.jd.survey.domain.security.User;
import com.jd.survey.service.security.UserService;

@RequestMapping("/security/groups")
@Controller
public class GroupController {
	
	//~ Static fields/initializers =========================================================

	private static final Log log = LogFactory.getLog(GroupController.class);	
	
	//~ Instance fields ====================================================================
	
	@Autowired	private MessageSource messageSource;
	
	@Autowired	private UserService userService;
	
	//~ Methods ============================================================================
	
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "/", params = "icreate", produces = "text/html")
	public String createInternalGet(Principal principal, Model uiModel, HttpServletRequest httpServletRequest) {
		try {
			User user = userService.user_findByLogin(principal.getName());
			Group group =  new Group(SecurityType.I);
			populateEditForm(uiModel, group,user);
			return "security/groups/create";
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw (new RuntimeException(e));
		}	
	}
	
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "/", params = "ecreate", produces = "text/html")
	public String createExternalGet(Principal principal, Model uiModel, HttpServletRequest httpServletRequest) {
		try {
			User user = userService.user_findByLogin(principal.getName());
			Group group =  new Group(SecurityType.E);
			populateEditForm(uiModel, group,user);
			return "security/groups/create";
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw (new RuntimeException(e));
		}	
	}
	
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
	public String createPost(@RequestParam(value = "_proceed", required = false) String proceed,
			@Valid Group group, 
			BindingResult bindingResult, 
			Principal principal, Model uiModel, HttpServletRequest httpServletRequest) {
		
		log.info("create(): handles " + RequestMethod.POST.toString());
		try {
			User user = userService.user_findByLogin(principal.getName());
			if(proceed != null){
				if (bindingResult.hasErrors()) {
					populateEditForm(uiModel, group,user);
					return "security/groups/create";
				}
				if (!userService.group_ValidateNameIsUnique(group)) {
					bindingResult.rejectValue("name", "field_unique");
					populateEditForm(uiModel, group,user);
					return "security/groups/create";
				}

				uiModel.asMap().clear();
				group = userService.group_merge(group);
				return "redirect:/security/groups/" + encodeUrlPathSegment(group.getId().toString(), httpServletRequest);
			} else {
				return "redirect:/security/groups" ; 
			}
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw (new RuntimeException(e));
		}
	}
	
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "/{id}", produces = "text/html")
	public String show(@PathVariable("id") Long id, Principal principal, Model uiModel) {
		try {
			uiModel.addAttribute("group", userService.group_findById(id));
			uiModel.addAttribute("isShow", true);
			uiModel.addAttribute("itemId", id);
			return "security/groups/show";
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw (new RuntimeException(e));
		}
	}

	@Secured({"ROLE_ADMIN"})
	@RequestMapping(produces = "text/html")
	public String list(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size, 
			Principal principal, Model uiModel) {
		
		try {
			if (page != null || size != null) {
				int sizeNo = size == null ? 10 : size.intValue();
				final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
				uiModel.addAttribute("groups", userService.group_findAll(firstResult, sizeNo));
				float nrOfPages = (float) userService.group_getCount() / sizeNo;
				uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
			} else {
				uiModel.addAttribute("groups", userService.group_findAll(null));
			}
			return "security/groups/list";
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw (new RuntimeException(e));
		}
	}

	@Secured({"ROLE_ADMIN"})
	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
	public String update(@RequestParam(value = "_proceed", required = false) String proceed,
			@Valid Group group, 
			BindingResult bindingResult, 
			Principal principal, Model uiModel, HttpServletRequest httpServletRequest) {
		
		log.info("update(): handles PUT");
		try{
			User user = userService.user_findByLogin(principal.getName());
			if(proceed != null){
				if (bindingResult.hasErrors()) {
					populateEditForm(uiModel, group,user);
					return "security/groups/update";
				}
				if (!userService.group_ValidateNameIsUnique(group)) {
					bindingResult.rejectValue("name", "field_unique");
					populateEditForm(uiModel, group,user);
					return "security/groups/update";
				}
				
				if(!userService.group_ValidateGroupEmpty(group)){
					bindingResult.rejectValue("authorities", "field_validation_checkboxes");
					populateEditForm(uiModel, group,user);
					return "security/groups/update";
				}
				uiModel.asMap().clear();
				group = userService.group_merge(group);
				log.info("redirecting to: " +  "redirect:/security/groups/" + encodeUrlPathSegment(group.getId().toString(), httpServletRequest));
				return "redirect:/security/groups/" + encodeUrlPathSegment(group.getId().toString(), httpServletRequest);
			} else {
				return "redirect:/security/groups" ; 
			}
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw (new RuntimeException(e));
		}
	}
	
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "/{id}", params = "form", produces = "text/html")
	public String updateForm(@PathVariable("id") Long id, Principal principal, Model uiModel) {
		log.info("updateForm(): id=" + id);
		try{
			User user = userService.user_findByLogin(principal.getName());	
			populateEditForm(uiModel, userService.group_findById(id), user);
			return "security/groups/update";
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw (new RuntimeException(e));
		}
	}
	
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
	public String delete(@PathVariable("id") Long id, Principal principal, Model uiModel, HttpServletRequest httpServletRequest) {
		log.info("delete(): id=" + id);
		try {
			Group group = userService.group_findById(id);
			Long groupId = group.getId();
			userService.group_remove(group);
			uiModel.asMap().clear();
			return "redirect:/security/groups";
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw (new RuntimeException(e));
		}
	}

	void populateEditForm(Model uiModel, Group group, User user) {
		log.info("populateEditForm()");
		try{
			uiModel.addAttribute("group", group);
			uiModel.addAttribute("authorities", userService.authority_findAll(group));
			uiModel.addAttribute("users", userService.user_findAll(group));
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw (new RuntimeException(e));
		}
	}

	String encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
		log.info("encodeUrlPathSegment()");
		try{
			String enc = httpServletRequest.getCharacterEncoding();
			if (enc == null) {
				enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
			}
			try {
				pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
			} catch (UnsupportedEncodingException uee) {log.error(uee);}
			return pathSegment;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw (new RuntimeException(e));
		}
	}

	@ExceptionHandler(RuntimeException.class)
	public String handleRuntimeException(RuntimeException ex, HttpServletRequest request) {
		log.error(ex);
		log.error("redirect to /uncaughtException");
		return "redirect:/uncaughtException";
	}
	
}