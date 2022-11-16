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
package com.jd.survey.zeus.service;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.jd.survey.dao.interfaces.security.UserDAO;
import com.jd.survey.domain.security.User;
import com.jd.survey.zeus.domain.ZeusUser;
import com.jd.survey.zeus.mapper.ZeusUserMapper;

@Transactional
public class ZeusUserDetailsService implements UserDetailsService  {
	
	//~ Static fields/initializers =========================================================

	private static final Log log = LogFactory.getLog(ZeusUserDetailsService.class);
	
	//~ Instance fields ====================================================================
	
	@Autowired private UserDAO userDAO;
	
	@Autowired private ZeusUserMapper<Serializable> zeusUserMapper;
	
	//~ Methods ============================================================================
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			User user = userDAO.findByLogin(username);
			if (user != null)  {
				ZeusUser zeusUser = zeusUserMapper.getUser(username);
				user.setPassword(zeusUser.getPassword());
				user.setSalt(zeusUser.getSalt());
				return user;
			}
			else {
				throw new UsernameNotFoundException("Could not find a user with the provided login");
			}
		}
		catch (Exception e)	{
			log.error(e);
			throw new RuntimeException(e);
		}
	}

}
