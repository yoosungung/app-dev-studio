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
package com.jd.survey.dao.interfaces.settings;

import java.util.Set;

import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

import com.jd.survey.domain.settings.DataSetItem;

public interface DataSetItemDAO extends JpaDao<DataSetItem> {

    public Set<DataSetItem> findAll() throws DataAccessException;

    public Set<DataSetItem> findAll(int startResult, int maxRows) throws DataAccessException;

    public DataSetItem findById(Long id) throws DataAccessException;

    public Long getCount(Long id) throws DataAccessException;

    public int deleteByDataSetId(Long id) throws DataAccessException;

    public Set<DataSetItem> findByDataSetId(Long id) throws DataAccessException;

    public Set<DataSetItem> findByDataSetId(Long id, int startResult, int maxRows) throws DataAccessException;

}