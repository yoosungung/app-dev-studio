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
package com.jd.survey.web.home;

import java.security.Principal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//@RequestMapping({ "/" })
//@Controller
public class HomeController {

    // ~ Static fields/initializers
    // =========================================================

    private static final Log log = LogFactory.getLog(HomeController.class);

    // ~ Methods
    // ============================================================================

    @RequestMapping(produces = "text/html")
    public String user(Model uiModel, Principal principal) {
        try {
            return "redirect:/admin";
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw (new RuntimeException(e));
        }
    }

    @Secured({ "ROLE_ADMIN", "ROLE_SURVEY_ADMIN" })
    @RequestMapping(value = "/admin", produces = "text/html")
    public String admin(Model uiModel, Principal principal) {
        try {
            return "redirect:/settings/surveyDefinitions";
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw (new RuntimeException(e));
        }
    }

}
