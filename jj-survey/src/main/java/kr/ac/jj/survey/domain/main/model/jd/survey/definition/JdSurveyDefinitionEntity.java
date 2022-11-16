package kr.ac.jj.survey.domain.main.model.jd.survey.definition;

import java.util.Date;

import kr.ac.jj.shared.domain.main.model.MainEntity;

/**
 * Entity
 */
abstract class JdSurveyDefinitionEntity extends MainEntity {

    private static final long serialVersionUID = 4254069022527695187L;

    protected Long id;
    protected String name;
    protected String purpose;
    protected String target;
    protected String description;
    protected Boolean isPublic;
    protected Boolean allowMultipleSubmissions;
    protected Date autoReminderLastSentDate;
    protected Integer autoRemindersDayOfMonth;
    protected String remindersFrequency;
    protected Integer autoRemindersMonthlyOccur;
    protected Integer autoRemindersWeeklyOccur;
    protected String completedSurveyTemplate;
    protected String emailInvitationTemplate;
    protected String logo;
    protected Integer sendAutoReminders;
    protected String status;
    protected String surveyTheme;
    protected Date startDt;
    protected Date endDt;
    protected Date registDt;
    protected Long departmentId;
    protected Integer version;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPurpose() {
        return this.purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getTarget() {
        return this.target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsPublic() {
        return this.isPublic;
    }

    public void setIsPublic(Boolean isPublic) {
        this.isPublic = isPublic;
    }

    public Boolean getAllowMultipleSubmissions() {
        return this.allowMultipleSubmissions;
    }

    public void setAllowMultipleSubmissions(Boolean allowMultipleSubmissions) {
        this.allowMultipleSubmissions = allowMultipleSubmissions;
    }

    public Date getAutoReminderLastSentDate() {
        return this.autoReminderLastSentDate;
    }

    public void setAutoReminderLastSentDate(Date autoReminderLastSentDate) {
        this.autoReminderLastSentDate = autoReminderLastSentDate;
    }

    public Integer getAutoRemindersDayOfMonth() {
        return this.autoRemindersDayOfMonth;
    }

    public void setAutoRemindersDayOfMonth(Integer autoRemindersDayOfMonth) {
        this.autoRemindersDayOfMonth = autoRemindersDayOfMonth;
    }

    public String getRemindersFrequency() {
        return this.remindersFrequency;
    }

    public void setRemindersFrequency(String remindersFrequency) {
        this.remindersFrequency = remindersFrequency;
    }

    public Integer getAutoRemindersMonthlyOccur() {
        return this.autoRemindersMonthlyOccur;
    }

    public void setAutoRemindersMonthlyOccur(Integer autoRemindersMonthlyOccur) {
        this.autoRemindersMonthlyOccur = autoRemindersMonthlyOccur;
    }

    public Integer getAutoRemindersWeeklyOccur() {
        return this.autoRemindersWeeklyOccur;
    }

    public void setAutoRemindersWeeklyOccur(Integer autoRemindersWeeklyOccur) {
        this.autoRemindersWeeklyOccur = autoRemindersWeeklyOccur;
    }

    public String getCompletedSurveyTemplate() {
        return this.completedSurveyTemplate;
    }

    public void setCompletedSurveyTemplate(String completedSurveyTemplate) {
        this.completedSurveyTemplate = completedSurveyTemplate;
    }

    public String getEmailInvitationTemplate() {
        return this.emailInvitationTemplate;
    }

    public void setEmailInvitationTemplate(String emailInvitationTemplate) {
        this.emailInvitationTemplate = emailInvitationTemplate;
    }

    public String getLogo() {
        return this.logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Integer getSendAutoReminders() {
        return this.sendAutoReminders;
    }

    public void setSendAutoReminders(Integer sendAutoReminders) {
        this.sendAutoReminders = sendAutoReminders;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSurveyTheme() {
        return this.surveyTheme;
    }

    public void setSurveyTheme(String surveyTheme) {
        this.surveyTheme = surveyTheme;
    }

    public Date getStartDt() {
        return this.startDt;
    }

    public void setStartDt(Date startDt) {
        this.startDt = startDt;
    }

    public Date getEndDt() {
        return this.endDt;
    }

    public void setEndDt(Date endDt) {
        this.endDt = endDt;
    }

    public Date getRegistDt() {
        return this.registDt;
    }

    public void setRegistDt(Date registDt) {
        this.registDt = registDt;
    }

    public Long getDepartmentId() {
        return this.departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getVersion() {
        return this.version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

}
