package com.jd.survey.zeus.service;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jd.survey.dao.interfaces.security.GroupDAO;
import com.jd.survey.dao.interfaces.security.UserDAO;
import com.jd.survey.dao.interfaces.settings.DepartmentDAO;
import com.jd.survey.dao.interfaces.settings.SurveyDefinitionDAO;
import com.jd.survey.domain.security.Group;
import com.jd.survey.domain.security.SecurityType;
import com.jd.survey.domain.security.ServiceType;
import com.jd.survey.domain.security.User;
import com.jd.survey.domain.settings.SurveyDefinition;
import com.jd.survey.pub.domain.SurveySecUserDefinition;
import com.jd.survey.service.security.UserService;
import com.jd.survey.zeus.domain.ZeusUser;
import com.jd.survey.zeus.mapper.ZeusUserMapper;
import com.jd.survey.zeus.web.cmd.ZeusUserCmd;

@Transactional(readOnly = true)
@Service("ZeusUserService")
public class ZeusUserService {

    @Autowired
    private ZeusUserMapper<Serializable> zeusUserMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private GroupDAO groupDAO;

    @Autowired
    private DepartmentDAO departmentDAO;

    @Autowired
    private SurveyDefinitionDAO surveyDefinitionDAO;

    @Autowired
    private UserDAO userDAO;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userDAO.findByLogin(username);
            if (user != null) {
                ZeusUser zeusUser = zeusUserMapper.getUser(username);
                user.setPassword(zeusUser.getPassword());
                user.setSalt(zeusUser.getSalt());
                return user;
            } else {
                throw new UsernameNotFoundException("Could not find a user with the provided login");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ZeusUserCmd searchUsers(ZeusUserCmd cmd) {
        cmd.setTotal(zeusUserMapper.searchUserCount(cmd));
        cmd.setSource(zeusUserMapper.searchUserList(cmd));
        return cmd;
    }

    public ZeusUserCmd searchSurveyUsers(ZeusUserCmd cmd) {
        ZeusUserCmd ZeusUserCmd = this.searchUsers(cmd);

        for (ZeusUser zeusUser : ZeusUserCmd.getPageList()) {
            for (SurveySecUserDefinition userSurveyDefinition : userService
                    .getUserSurveyDefinitionsBySurveyDefinitionId(cmd.getSurveyId())) {
                if (zeusUser.getUserId().equals(userSurveyDefinition.getLogin())) {
                    zeusUser.setSurveyDefinitionId(String.valueOf(userSurveyDefinition.getSurveyDefinitionId()));
                }
            }

        }
        return ZeusUserCmd;
    }

    public List<ZeusUser> getAllUserList() {
        return zeusUserMapper.getAllUserList();
    }

    @Transactional(readOnly = false)
    public Map<String, String> addUserServey(String userId, String surveyId) {
        Map<String, String> map = new HashMap<String, String>();

        User user = userService.user_findByLogin(userId);

        if (user == null) {
            if (zeusUserMapper.getUserCount(userId) > 0) {

                ZeusUser zeusUser = zeusUserMapper.getUser(userId);

                if (zeusUser.getEmail() == null) {
                    map.put("fail", "이메일이 등록되지 않은 사용자는 등록할 수 없습니다.");
                    map.put("noEmail", "1");
                } else if (userService.user_findByEmail(zeusUser.getEmail()) != null) {
                    map.put("fail", "동일한 이메일을 사용하는 사용자가 있어 등록할 수 없습니다.");
                    map.put("overLapEmail", "1");
                } else {

                    user = new User(SecurityType.E);
                    user.setLogin(zeusUser.getUserId());
                    user.setFirstName(zeusUser.getUserNm());
                    user.setEmail(zeusUser.getEmail());

                    user.setMiddleName("middleName");
                    user.setLastName("lastName");
                    user.setDateOfBirth(new Date());
                    user.setPassword("zeus");
                    user.setServiceType(ServiceType.ZEUS);
                    user.setEnabled(true);

                    SortedSet<Group> groups = new TreeSet<Group>();
                    groups.add(groupDAO.findById(3l));
                    user.setGroups(groups);

                    SortedSet<SurveyDefinition> surveyDefinitions = new TreeSet<SurveyDefinition>();
                    surveyDefinitions.add(surveyDefinitionDAO.findById(Long.valueOf(surveyId).longValue()));
                    user.setSurveyDefinitions(surveyDefinitions);

                    userService.user_merge(user);
                    map.put("success", "사용자 최초등록");
                    map.put("successCnt", "1");
                }
            } else {
                map.put("fail", "등록이 불가능한 대상입니다.");
                map.put("noTagert", "1");
            }
        } else {
            if (userService.getUserSurveyDefinitionCount(Long.valueOf(surveyId).longValue(), user.getId()) == 0) {
                userService.addUserSurveyDefinition(Long.valueOf(surveyId).longValue(), user.getId());
                map.put("success", "기등록 대상자");
                map.put("successCnt", "1");
            }
        }
        return map;
    }

    @Transactional(readOnly = false)
    public String addAllUserServey(String surveyId) {
        int result = 0;
        int noEmailCnt = 0;
        int overLapEmailCnt = 0;
        int noTargetCnt = 0;

        for (ZeusUser zeusUser : this.getAllUserList()) {
            Map<String, String> map = this.addUserServey(zeusUser.getUserId(), surveyId);
            if (map.get("successCnt") != null) {
                result += Integer.valueOf(map.get("successCnt")).intValue();
            } else if (map.get("fail") != null) {
                if (map.get("noEmail") != null) {
                    noEmailCnt += Integer.valueOf(map.get("noEmail")).intValue();
                } else if (map.get("overLapEmail") != null) {
                    overLapEmailCnt += Integer.valueOf(map.get("overLapEmail")).intValue();
                } else {
                    noTargetCnt += Integer.valueOf(map.get("noTagert")).intValue();
                }
            }
        }

        System.out.println("이메일 미등록인원 : " + noEmailCnt + "명");
        System.out.println("이메일 중복인원 : " + overLapEmailCnt + "명");
        System.out.println("등록불가 인원 : " + noTargetCnt + "명");
        return String.valueOf(result);
    }

    /*@Transactional(readOnly = false)
    public void userMigration() {
    
        int add = 0;
        int del = 0;
        int dupId = 0;
        int dupEmail = 0;
        int empty = 0;
        int exception = 0;
    
        List<ZeusUser> zeusUsers = zeusUserMapper.getUsers();
        SortedSet<Group> groups = new TreeSet<Group>();
        groups.add(groupDAO.findById(3l));
    
        for(ZeusUser zeusUser : zeusUsers) {
            try {
                System.out.println(zeusUser.getUserId());
                if(zeusUser.getSecedeConfmDt() == null) {
                    if(userService.user_findByLogin(zeusUser.getUserId()) == null) {
                        if(zeusUser.getEmail() != null && userService.user_findByEmail(zeusUser.getEmail()) == null) {
                            User user = new User(SecurityType.E);
                            user.setLogin(zeusUser.getUserId());
                            user.setFirstName(zeusUser.getKorNm());
                            user.setEmail(zeusUser.getEmail());
    
                            user.setMiddleName("middleName");
                            user.setLastName("lastName");
                            user.setDateOfBirth(new Date());
                            user.setPassword("zeus");
                            user.setServiceType(ServiceType.ZEUS);
                            user.setEnabled(true);
    
                            user.setGroups(groups);
                            userService.user_merge(user);
                            add++;
                            System.out.println("ADD : " + user.getLogin());
                        }
                        else dupEmail++;
                    }
                    else dupId++;
                }
                else if(zeusUser.getSecedeConfmDt() != null) {
                    User user = userService.user_findByLogin(zeusUser.getUserId());
                    if(user != null) {
                        userService.user_remove(user);
                        del++;
                    }
                    else empty++;
                }
            } catch(Exception e) {
                e.printStackTrace();
                System.out.println("EXCEPTION : " + zeusUser);
                exception++;
            }
        }
    
        System.out.println("사용자 등록 : " + add + "명");
        System.out.println("사용자 삭제 : " + del + "명");
        System.out.println("사용자 아이디중복 : " + dupId + "명");
        System.out.println("사용자 이메일중복 : " + dupEmail + "명");
        System.out.println("사용자 이미삭제 : " + empty + "명");
        System.out.println("오류 : " + exception + "명");
    }*/

}
