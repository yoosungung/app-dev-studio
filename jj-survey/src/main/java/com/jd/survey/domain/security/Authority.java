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
package com.jd.survey.domain.security;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "survey_sec_authority")
@NamedQueries({
        @NamedQuery(name = "Authority.findAll", query = "select o from Authority o order by o.type desc,o.name asc"),
        @NamedQuery(name = "Authority.findAllInternal", query = "select o from Authority o where o.type = com.jd.survey.domain.security.SecurityType.I order by o.name asc"),
        @NamedQuery(name = "Authority.findAllExternal", query = "select o from Authority o where o.type = com.jd.survey.domain.security.SecurityType.E order by o.name asc"),
        @NamedQuery(name = "Authority.findById", query = "select o from Authority o where o.id = ?1"),
        @NamedQuery(name = "Authority.findByName", query = "select o from Authority o where o.name = ?1"),
        @NamedQuery(name = "Authority.getCount", query = "select count(o) from Authority o"),
        @NamedQuery(name = "Authority.getbyUserId", query = "select a from User u inner join u.groups g inner join g.authorities a where u.id = ?1") })
public class Authority implements GrantedAuthority, Comparable<Authority>, Serializable, SecurityObject {

    // ~ Static fields/initializers
    // =========================================================

    private static final long serialVersionUID = 6083393202423980337L;

    // ~ Default Instance fields
    // ============================================================

    @Id
    // @SequenceGenerator(name = "seqSecAuthority",
    // sequenceName="seq_survey_sec_authority")
    // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =
    // "seqSecAuthority")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Version
    @Column(name = "version")
    private Integer version;

    @Enumerated(EnumType.STRING)
    @Column(name = "AUTHORITY_TYPE")
    private SecurityType type;

    @NotEmpty
    @Size(max = 75)
    @Column(unique = true, length = 75, nullable = false)
    private String name;

    @Size(max = 500)
    @Column(length = 500)
    private String description;

    // ~ Instance fields
    // ====================================================================

    // ~ Constructors
    // =======================================================================

    // ~ Methods
    // ============================================================================

    public String toString() {
        return this.name;
    }

    @Override
    public String getAuthority() {
        return this.name;
    }

    // Comparable interface
    @Override
    public int compareTo(Authority that) {

        final int BEFORE = -1;
        final int AFTER = 1;
        if (that == null) {
            return BEFORE;
        }
        Comparable<String> thisAuthority = this.getName();
        Comparable<String> thatAuthority = that.getName();
        if (thisAuthority == null) {
            return AFTER;
        } else if (thatAuthority == null) {
            return BEFORE;
        } else {
            return thisAuthority.compareTo(that.getName());
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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public SecurityType getType() {
        return type;
    }

    @Override
    public void setType(SecurityType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
