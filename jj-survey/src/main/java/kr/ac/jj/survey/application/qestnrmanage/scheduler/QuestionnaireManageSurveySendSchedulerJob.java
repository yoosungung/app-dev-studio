package kr.ac.jj.survey.application.qestnrmanage.scheduler;

import java.util.List;

import org.apache.ibatis.session.ResultContext;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.ac.jj.shared.domain.main.mapper.com.dept.TbComDeptMapper;
import kr.ac.jj.shared.domain.main.mapper.com.person.TbComPersonMapper;
import kr.ac.jj.shared.domain.main.model.com.dept.TbComDept;
import kr.ac.jj.shared.domain.main.model.com.person.TbComPerson;
import kr.ac.jj.shared.infrastructure.framework.core.support.scheduling.BaseJob;
import kr.ac.jj.shared.infrastructure.framework.core.support.scheduling.model.JobDetailTrigger;
import kr.ac.jj.shared.infrastructure.framework.core.support.scheduling.model.SchedulerJobConfig;
import kr.ac.jj.shared.infrastructure.framework.mybatis.persistence.dao.handler.DataResultHandler;
import kr.ac.jj.survey.application.qestnrmanage.service.QuestionnaireManageServiceImpl;
import kr.ac.jj.survey.domain.main.model.survey.qestnr.TbSurveyQestnr;
import kr.ac.jj.survey.domain.main.model.survey.qestnr.person.TbSurveyQestnrPersonToPerson;

/**
 * 설문지 관리 - 설문 발송 처리 SchedulerJob
 */
@Component
@DisallowConcurrentExecution
public class QuestionnaireManageSurveySendSchedulerJob extends BaseJob {

    private @Autowired QuestionnaireManageServiceImpl questionnaireManageService;
    private @Autowired TbComPersonMapper tbComPersonMapper;
    private @Autowired TbComDeptMapper tbComDeptMapper;

    @Override
    public void executeJob(SchedulerJobConfig jobConfig, JobDetailTrigger jobDetailTrigger,
            JobExecutionContext context) {
        this.createSurveySend();
    }

    public void createSurveySend() {
        List<TbSurveyQestnr> tbSurveyQestnrList = questionnaireManageService.readSurveySendTargetList();

        for (TbSurveyQestnr tbSurveyQestnr : tbSurveyQestnrList) {
            TbComPerson registPerson = tbComPersonMapper.select(tbSurveyQestnr.getRegistPsnId());
            TbComDept registPersonDept = tbComDeptMapper.select(registPerson.getDeptId());

            questionnaireManageService.readSurveySendTargetPersonList(tbSurveyQestnr.getSurveyQestnrId(),
                    new DataResultHandler<TbSurveyQestnrPersonToPerson>() {

                        @Override
                        public void handleResult(ResultContext<? extends TbSurveyQestnrPersonToPerson> resultContext) {
                            questionnaireManageService.createSurveySend(tbSurveyQestnr, resultContext.getResultObject(),
                                    registPerson, registPersonDept, null);
                        }

                    });
        }
    }

}
