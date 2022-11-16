package kr.ac.jj.survey.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.jd.survey.GlobalSettings;
import com.jd.survey.domain.security.Authority;
import com.jd.survey.domain.security.Group;
import com.jd.survey.domain.security.User;
import com.jd.survey.domain.settings.DataSet;
import com.jd.survey.domain.settings.DataSetItem;
import com.jd.survey.domain.settings.Day;
import com.jd.survey.domain.settings.Department;
import com.jd.survey.domain.settings.GroupingOperator;
import com.jd.survey.domain.settings.LogicOperator;
import com.jd.survey.domain.settings.Question;
import com.jd.survey.domain.settings.QuestionOption;
import com.jd.survey.domain.settings.QuestionType;
import com.jd.survey.domain.settings.RegularExpression;
import com.jd.survey.domain.settings.Sector;
import com.jd.survey.domain.settings.SurveyDefinition;
import com.jd.survey.domain.settings.SurveyDefinitionPage;
import com.jd.survey.domain.settings.SurveyTemplate;
import com.jd.survey.domain.settings.VelocityTemplate;
import com.jd.survey.service.security.UserService;
import com.jd.survey.service.settings.ApplicationSettingsService;
import com.jd.survey.service.settings.SurveySettingsService;
import com.jd.survey.web.DateTimeFormatAnnotationFormatterFactory;

@Configuration
public class ApplicationFormatterConfig implements WebMvcConfigurer {

    private static final Logger log = LoggerFactory.getLogger(ApplicationFormatterConfig.class);

    private @Autowired ApplicationContext applicationContext;
    private @Autowired SurveySettingsService surveySettingsService;
    private @Autowired ApplicationSettingsService applicationSettingsService;
    private @Autowired UserService userService;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new Converter<VelocityTemplate, String>() {

            public String convert(VelocityTemplate velocityTemplate) {
                log.info("converting VelocityTemplate to String");
                return new StringBuilder().append(velocityTemplate.getName()).toString();
            }

        });

        registry.addConverter(new Converter<Long, VelocityTemplate>() {

            public VelocityTemplate convert(Long id) {
                log.info("converting Long to VelocityTemplate id=" + id + " result="
                        + surveySettingsService.velocityTemplate_findById(id).toString());
                return surveySettingsService.velocityTemplate_findById(id);
            }

        });

        registry.addConverter(new Converter<String, VelocityTemplate>() {

            public VelocityTemplate convert(String id) {
                log.info("converting String to VelocityTemplate id=" + id);
                ConversionService conversionService = applicationContext.getBean(ConversionService.class);
                return conversionService.convert(conversionService.convert(id, Long.class), VelocityTemplate.class);
            }

        });

        registry.addConverter(new Converter<Department, String>() {

            public String convert(Department department) {
                log.info("converting Department to String");
                return new StringBuilder().append(department.getName()).toString();
            }

        });

        registry.addConverter(new Converter<Long, Department>() {

            public Department convert(Long id) {
                log.info("converting Long to Department id=" + id + " result="
                        + surveySettingsService.department_findById(id).toString());
                return surveySettingsService.department_findById(id);
            }

        });

        registry.addConverter(new Converter<String, Department>() {

            public Department convert(String id) {
                log.info("converting String to Department id=" + id);
                ConversionService conversionService = applicationContext.getBean(ConversionService.class);
                return conversionService.convert(conversionService.convert(id, Long.class), Department.class);
            }

        });

        registry.addConverter(new Converter<SurveyDefinition, String>() {

            public String convert(SurveyDefinition surveyDefinition) {
                log.info("converting SurveyDefinition to String");
                return new StringBuilder().append(surveyDefinition.getName()).toString();
            }

        });

        registry.addConverter(new Converter<Long, SurveyDefinition>() {

            public SurveyDefinition convert(Long id) {
                log.info("converting Long to SurveyDefinition id=" + id + " result="
                        + surveySettingsService.surveyDefinition_findById(id).toString());
                return surveySettingsService.surveyDefinition_findById(id);
            }

        });

        registry.addConverter(new Converter<String, SurveyDefinition>() {

            public SurveyDefinition convert(String id) {
                log.info("converting String to SurveyDefinition id=" + id);
                ConversionService conversionService = applicationContext.getBean(ConversionService.class);
                return conversionService.convert(conversionService.convert(id, Long.class), SurveyDefinition.class);
            }

        });

        registry.addConverter(new Converter<QuestionOption, String>() {

            public String convert(QuestionOption questionOption) {
                log.info("converting QuestionOption to String");
                return new StringBuilder().append(questionOption.getText()).toString();
            }

        });

        registry.addConverter(new Converter<Long, QuestionOption>() {

            public QuestionOption convert(Long id) {
                log.info("converting Long to QuestionOption id=" + id + " result="
                        + surveySettingsService.questionOption_findById(id).toString());
                return surveySettingsService.questionOption_findById(id);
            }

        });

        registry.addConverter(new Converter<String, QuestionOption>() {

            public QuestionOption convert(String id) {
                log.info("converting String to QuestionOption id=" + id);
                ConversionService conversionService = applicationContext.getBean(ConversionService.class);
                return conversionService.convert(conversionService.convert(id, Long.class), QuestionOption.class);
            }

        });

        registry.addConverter(new Converter<QuestionType, String>() {

            public String convert(QuestionType questionType) {
                log.info("converting QuestionType to String");
                return questionType.getCode();
            }

        });

        registry.addConverter(new Converter<String, QuestionType>() {

            public QuestionType convert(String id) {
                log.info("converting String to QuestionType id=" + id);
                return QuestionType.fromCode(id);
            }

        });

        registry.addConverter(new Converter<Question, String>() {

            public String convert(Question question) {
                log.info("converting Question to String");
                return new StringBuilder().append(question.toString()).toString();
            }

        });

        registry.addConverter(new Converter<Long, Question>() {

            public Question convert(Long id) {
                log.info("converting Long to Question id=" + id + " result="
                        + surveySettingsService.question_findById(id).toString());
                return surveySettingsService.question_findById(id);
            }

        });

        registry.addConverter(new Converter<String, Question>() {

            public Question convert(String id) {
                log.info("converting String to Question id=" + id);
                ConversionService conversionService = applicationContext.getBean(ConversionService.class);
                return conversionService.convert(conversionService.convert(id, Long.class), Question.class);
            }

        });

        registry.addConverter(new Converter<SurveyDefinitionPage, String>() {

            public String convert(SurveyDefinitionPage surveyDefinitionPage) {
                log.info("converting SurveyDefinitionPage to String");
                return new StringBuilder().append(surveyDefinitionPage.toString()).toString();
            }

        });

        registry.addConverter(new Converter<Long, SurveyDefinitionPage>() {

            public SurveyDefinitionPage convert(Long id) {
                log.info("converting Long to SurveyDefinitionPage id=" + id + " result="
                        + surveySettingsService.surveyDefinitionPage_findById(id).toString());
                return surveySettingsService.surveyDefinitionPage_findById(id);
            }

        });

        registry.addConverter(new Converter<String, SurveyDefinitionPage>() {

            public SurveyDefinitionPage convert(String id) {
                log.info("converting String to SurveyDefinitionPage id=" + id);
                ConversionService conversionService = applicationContext.getBean(ConversionService.class);
                return conversionService.convert(conversionService.convert(id, Long.class), SurveyDefinitionPage.class);
            }

        });

        registry.addConverter(new Converter<User, String>() {

            public String convert(User user) {
                log.info("converting User to String");
                return new StringBuilder().append(user.toString()).toString();
            }

        });

        registry.addConverter(new Converter<Long, User>() {

            public User convert(Long id) {
                log.info("converting Long to User id=" + id + " result=" + userService.user_findById(id).toString());
                return userService.user_findById(id);
            }

        });

        registry.addConverter(new Converter<String, User>() {

            public User convert(String id) {
                log.info("converting String to User id=" + id);
                ConversionService conversionService = applicationContext.getBean(ConversionService.class);
                return conversionService.convert(conversionService.convert(id, Long.class), User.class);
            }

        });

        registry.addConverter(new Converter<Group, String>() {

            public String convert(Group group) {
                log.info("converting Group to String");
                return new StringBuilder().append(group.toString()).toString();
            }

        });

        registry.addConverter(new Converter<Long, Group>() {

            public Group convert(Long id) {
                log.info("converting Long to Group id=" + id + " result=" + userService.group_findById(id).toString());
                return userService.group_findById(id);
            }

        });

        registry.addConverter(new Converter<String, Group>() {

            public Group convert(String id) {
                log.info("converting String to Group id=" + id);
                ConversionService conversionService = applicationContext.getBean(ConversionService.class);
                return conversionService.convert(conversionService.convert(id, Long.class), Group.class);
            }

        });

        registry.addConverter(new Converter<Authority, String>() {

            public String convert(Authority authority) {
                log.info("converting Authority to String");
                return new StringBuilder().append(authority.toString()).toString();
            }

        });

        registry.addConverter(new Converter<Long, Authority>() {

            public Authority convert(Long id) {
                log.info("converting Long to Authority id=" + id + " result="
                        + userService.authority_findById(id).toString());
                return userService.authority_findById(id);
            }

        });

        registry.addConverter(new Converter<String, Authority>() {

            public Authority convert(String id) {
                log.info("converting String to Authority id=" + id);
                ConversionService conversionService = applicationContext.getBean(ConversionService.class);
                return conversionService.convert(conversionService.convert(id, Long.class), Authority.class);
            }

        });

        registry.addConverter(new Converter<DataSet, String>() {

            public String convert(DataSet dataSet) {
                log.info("converting DataSet to String");
                return new StringBuilder().append(dataSet.getName()).toString();
            }

        });

        registry.addConverter(new Converter<Long, DataSet>() {

            public DataSet convert(Long id) {
                log.info("converting Long to DataSet id=" + id + " result="
                        + surveySettingsService.velocityTemplate_findById(id).toString());
                return surveySettingsService.dataSet_findById(id);
            }

        });

        registry.addConverter(new Converter<String, DataSet>() {

            public DataSet convert(String id) {
                log.info("converting String to DataSet id=" + id);
                ConversionService conversionService = applicationContext.getBean(ConversionService.class);
                return conversionService.convert(conversionService.convert(id, Long.class), DataSet.class);
            }

        });

        registry.addConverter(new Converter<DataSetItem, String>() {

            public String convert(DataSetItem dataSetItem) {
                log.info("converting DataSetItem to String");
                return new StringBuilder().append(dataSetItem.getText()).toString();
            }

        });

        registry.addConverter(new Converter<Long, DataSetItem>() {

            public DataSetItem convert(Long id) {
                log.info("converting Long to DataSetItem id=" + id + " result="
                        + surveySettingsService.velocityTemplate_findById(id).toString());
                return surveySettingsService.datasetItem_findById(id);
            }

        });

        registry.addConverter(new Converter<String, DataSetItem>() {

            public DataSetItem convert(String id) {
                log.info("converting String to DataSetItem id=" + id);
                ConversionService conversionService = applicationContext.getBean(ConversionService.class);
                return conversionService.convert(conversionService.convert(id, Long.class), DataSetItem.class);
            }

        });

        registry.addConverter(new Converter<RegularExpression, String>() {

            public String convert(RegularExpression regularExpression) {
                log.info("converting regularExpression to String");
                return new StringBuilder().append(regularExpression.getName()).toString();
            }

        });

        registry.addConverter(new Converter<Long, RegularExpression>() {

            public RegularExpression convert(Long id) {
                log.info("converting Long to RegularExpression id=" + id);
                return surveySettingsService.regularExpression_findById(id);
            }

        });

        registry.addConverter(new Converter<String, RegularExpression>() {

            public RegularExpression convert(String id) {
                log.info("converting String to RegularExpression id=" + id);
                ConversionService conversionService = applicationContext.getBean(ConversionService.class);
                return conversionService.convert(conversionService.convert(id, Long.class), RegularExpression.class);
            }

        });

        // custom DateTimeFormatter
        DateTimeFormatAnnotationFormatterFactory dateTimeFormatAnnotationFormatterFactory = new DateTimeFormatAnnotationFormatterFactory();
        dateTimeFormatAnnotationFormatterFactory.setApplicationContext(applicationContext);
        registry.addFormatterForFieldAnnotation(dateTimeFormatAnnotationFormatterFactory);

        registry.addConverter(new Converter<GroupingOperator, String>() {

            public String convert(GroupingOperator groupingOperator) {
                log.info("converting QuestionType to String");
                return groupingOperator.getCode();
            }

        });

        registry.addConverter(new Converter<String, GroupingOperator>() {

            public GroupingOperator convert(String id) {
                log.info("converting String to GroupingOperator id=" + id);
                return GroupingOperator.fromCode(id);
            }

        });

        registry.addConverter(new Converter<LogicOperator, String>() {

            public String convert(LogicOperator logicOperator) {
                log.info("converting LogicOperator to String");
                return logicOperator.getCode();
            }

        });

        registry.addConverter(new Converter<String, LogicOperator>() {

            public LogicOperator convert(String id) {
                log.info("converting String to LogicOperator type id=" + id);
                return LogicOperator.fromCode(id);
            }

        });

        registry.addConverter(new Converter<Sector, String>() {

            public String convert(Sector sector) {
                log.info("converting Sector to String");
                return new StringBuilder().append(sector.getName()).toString();
            }

        });

        registry.addConverter(new Converter<Long, Sector>() {

            public Sector convert(Long id) {
                log.info("converting Long to Sector id=" + id + " result=" + surveySettingsService.sector_findById(id));
                return surveySettingsService.sector_findById(id);
            }

        });

        registry.addConverter(new Converter<String, Sector>() {

            public Sector convert(String id) {
                log.info("converting String to Sector id=" + id);
                ConversionService conversionService = applicationContext.getBean(ConversionService.class);
                return conversionService.convert(conversionService.convert(id, Long.class), Sector.class);
            }

        });

        registry.addConverter(new Converter<SurveyTemplate, String>() {

            public String convert(SurveyTemplate surveyTemplate) {
                log.info("converting SurveyTemplate to String");
                return new StringBuilder().append(surveyTemplate.getName()).toString();
            }

        });

        registry.addConverter(new Converter<Long, SurveyTemplate>() {

            public SurveyTemplate convert(Long id) {
                log.info("converting Long to SurveyTemplate id=" + id + " result="
                        + surveySettingsService.surveyTemplate_findById(id));
                return surveySettingsService.surveyTemplate_findById(id);
            }

        });

        registry.addConverter(new Converter<String, SurveyTemplate>() {

            public SurveyTemplate convert(String id) {
                log.info("converting String to SurveyTemplate id=" + id);
                ConversionService conversionService = applicationContext.getBean(ConversionService.class);
                return conversionService.convert(conversionService.convert(id, Long.class), SurveyTemplate.class);
            }

        });

        registry.addConverter(new Converter<Day, String>() {

            public String convert(Day day) {
                return new StringBuilder().append(day.getDayName()).toString();
            }

        });

        registry.addConverter(new Converter<Long, Day>() {

            public Day convert(Long id) {
                log.info("converting Long to Day id=" + id);
                return surveySettingsService.day_findById(id);
            }

        });

        registry.addConverter(new Converter<String, Day>() {

            public Day convert(String id) {
                log.info("converting String to Day id=" + id);
                ConversionService conversionService = applicationContext.getBean(ConversionService.class);
                return conversionService.convert(conversionService.convert(id, Long.class), Day.class);
            }

        });

        registry.addConverter(new Converter<GlobalSettings, String>() {

            public String convert(GlobalSettings globalSettings) {
                log.info("converting Department to String");
                return new StringBuilder().append(globalSettings.getPasswordEnforcementRegex()).toString();
            }

        });

        registry.addConverter(new Converter<Long, GlobalSettings>() {

            public GlobalSettings convert(Long id) {
                log.info("converting Long to globalSettings id=" + id + " result="
                        + applicationSettingsService.globalSettings_findById(id).toString());
                return applicationSettingsService.globalSettings_findById(id);
            }

        });

        registry.addConverter(new Converter<String, GlobalSettings>() {

            public GlobalSettings convert(String id) {
                log.info("converting String to Department id=" + id);
                ConversionService conversionService = applicationContext.getBean(ConversionService.class);
                return conversionService.convert(conversionService.convert(id, Long.class), GlobalSettings.class);
            }

        });
    }

}
