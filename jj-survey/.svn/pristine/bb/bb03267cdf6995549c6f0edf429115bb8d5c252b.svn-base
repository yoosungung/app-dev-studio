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
package com.jd.survey.service.settings;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jd.survey.GlobalSettings;
import com.jd.survey.dao.interfaces.settings.GlobalSettingsDAO;

@Transactional(readOnly = true)
@Service("ApplicationSettingsService")
public class ApplicationSettingsService {

    // ~ Static fields/initializers
    // =========================================================

    // ~ Default Instance fields
    // ============================================================

    @Autowired
    private GlobalSettingsDAO globalSettingsDAO;

    // ~ Instance fields
    // ====================================================================

    // ~ Constructors
    // =======================================================================

    // ~ Methods
    // ============================================================================

    public Long globalSettings_getCount() {
        return globalSettingsDAO.getCount();
    }

    @Transactional(readOnly = false)
    public GlobalSettings globalSettings_merge(GlobalSettings globalSettings) {
        GlobalSettings dbGlobal = globalSettingsDAO.findById(globalSettings.getId());
        dbGlobal.setPasswordEnforcementRegex(globalSettings.getPasswordEnforcementRegex());
        dbGlobal.setPasswordEnforcementMessage(globalSettings.getPasswordEnforcementMessage());
        dbGlobal.setValidContentTypes(globalSettings.getValidContentTypes());
        dbGlobal.setValidImageTypes(globalSettings.getValidImageTypes());
        dbGlobal.setMaximunFileSize(globalSettings.getMaximunFileSize());
        dbGlobal.setInvalidContentMessage(globalSettings.getInvalidContentMessage());
        dbGlobal.setInvalidFileSizeMessage(globalSettings.getInvalidFileSizeMessage());
        return globalSettingsDAO.merge(dbGlobal);
    }

    @Transactional(readOnly = false)
    public Set<GlobalSettings> globalSettings_findAll(int startResult, int maxRows) {
        return globalSettingsDAO.findAll(startResult, maxRows);
    }

    @Transactional(readOnly = false)
    public GlobalSettings globalSettings_findById(Long id) {
        return globalSettingsDAO.findById(id);
    }

    @Transactional(readOnly = false)
    public Set<GlobalSettings> globalSettings_findAll() {
        return globalSettingsDAO.findAll();
    }

    @Transactional(readOnly = true)
    public GlobalSettings getSettings() {
        GlobalSettings globalSettings = globalSettingsDAO.findById(1l);
        return globalSettings;
    }

}
