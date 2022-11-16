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
package com.jd.survey.domain.survey;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@NamedQueries({
	@NamedQuery(name = "SurveyDocument.findById", query = "select o from SurveyDocument o where o.id = ?1"),
	@NamedQuery(name = "SurveyDocument.findBySurveyIdAndQuestionId", query = "select o from SurveyDocument o where o.surveyId = ?1 and o.questionId= ?2"),
	@NamedQuery(name = "SurveyDocument.deleteBySurveyIdAndQuestionId", query = "delete from SurveyDocument o where o.surveyId = ?1 and o.questionId= ?2")
})
public class SurveyDocument implements Serializable {
	
	//~ Static fields/initializers =========================================================

	private static final long serialVersionUID = 4260729219803926889L;
	
	//~ Default Instance fields ============================================================
	
	@Id
	//@SequenceGenerator(name = "seqServeyDocument", sequenceName="seq_servey_document")
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqServeyDocument")
	@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
	
	@Version
    @Column(name = "version")
    private Integer version;
	
	@NotNull
	@Column(name = "SURVEY_ID")
	Long surveyId;
	
	@NotNull
	@Column(name = "QUESTION_ID")
	Long questionId;
	
	@Lob
	private byte[]  content;
	
	@NotNull
	@Size(max = 50)
	private String fileName;
	
	@NotNull
	@Size(max = 250)
	private String contentType;
	
	@DateTimeFormat(pattern="#{messages['date_format']}")
	@Column(nullable= false)
	private Date creationDate;

	//~ Instance fields ====================================================================

	//~ Constructors =======================================================================
	
	public SurveyDocument() {
		super();
	}
	
	public SurveyDocument(Long surveyId, Long questionId, String fileName, String contentType, byte[] content) {
		super();
		this.surveyId = surveyId;
		this.questionId =questionId;
		this.fileName = fileName;
		this.contentType = contentType;
		this.content  = content;
		this.creationDate = new Date();
	}
	
	//~ Methods ============================================================================
	
	//~ Default Methods ====================================================================

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

	public Long getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(Long surveyId) {
		this.surveyId = surveyId;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
}
