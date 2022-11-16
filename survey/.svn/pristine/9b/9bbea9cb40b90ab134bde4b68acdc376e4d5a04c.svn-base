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
package com.jd.survey.domain.settings;

public enum QuestionColType {
	
	SHORT_TEXT_INPUT("ST"),
	INTEGER_INPUT("IN"),
	CURRENCY_INPUT("CR"),
	DECIMAL_INPUT("NM"),
	DATE_INPUT("DT");
	
	private String code;
	 
	private QuestionColType(String c) {
	   code = c;
	}
	 
	public String getCode() {
	  return code;
	}
 	
	public static QuestionColType fromCode(String code) {
		if (code != null) {
			for (QuestionColType b : QuestionColType.values()) {
				if (code.equalsIgnoreCase(b.code)) {
					return b;
				}
			}
		}
		return null;
	}
	
}