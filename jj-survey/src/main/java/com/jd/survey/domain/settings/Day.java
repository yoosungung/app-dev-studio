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

import java.io.Serializable;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;

@Entity
@Table(name = "survey_day")
@NamedQueries({ @NamedQuery(name = "Day.findAll", query = "select o from Day o"),
        @NamedQuery(name = "Day.findById", query = "select o from Day o where o.id = ?1"),
        @NamedQuery(name = "Day.findByDayName", query = "select o from Day o where  o.dayName = ?1") })
public class Day implements Comparable<Day>, Serializable {

    // ~ Static fields/initializers
    // =========================================================

    private static final long serialVersionUID = 8386552512916837008L;

    // ~ Default Instance fields
    // ============================================================

    @Id
    // @SequenceGenerator(name = "seqDay", sequenceName="seq_survey_day")
    // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqDay")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Version
    @Column(name = "version")
    private Integer version;

    @NotBlank
    @Column(unique = true, length = 10, nullable = false)
    @Size(max = 10)
    private String dayName;

    // ~ Instance fields
    // ====================================================================

    @NotNull
    @ManyToMany
    @Sort(type = SortType.NATURAL)
    @JoinTable(name = "survey_definition_reminders", joinColumns = {
            @JoinColumn(name = "day_id", referencedColumnName = "id") }, inverseJoinColumns = {
                    @JoinColumn(name = "surveyDefinition_id", referencedColumnName = "id") })
    private SortedSet<SurveyDefinition> surveyDefinitions = new TreeSet<SurveyDefinition>();

    // ~ Constructors
    // =======================================================================

    // ~ Methods
    // ============================================================================

    // Comparable interface
    @Override
    public int compareTo(Day that) {

        final int BEFORE = -1;
        final int AFTER = 1;
        if (that == null) {
            return BEFORE;
        }
        Comparable<Long> thisDay = this.getId();
        Comparable<Long> thatDay = that.getId();
        if (thisDay == null) {
            return AFTER;
        } else if (thatDay == null) {
            return BEFORE;
        } else {
            return thisDay.compareTo(that.getId());
        }

    }

    // ~ Default Methods
    // ====================================================================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public SortedSet<SurveyDefinition> getSurveyDefinitions() {
        return surveyDefinitions;
    }

    public void setSurveyDefinitions(SortedSet<SurveyDefinition> surveyDefinitions) {
        this.surveyDefinitions = surveyDefinitions;
    }

}
