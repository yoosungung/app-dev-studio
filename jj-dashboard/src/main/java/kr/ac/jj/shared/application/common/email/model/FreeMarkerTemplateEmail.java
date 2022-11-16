package kr.ac.jj.shared.application.common.email.model;

import org.apache.commons.lang3.StringUtils;

import kr.ac.jj.shared.infrastructure.template.util.FreeMarkerTemplateProcessor;

public class FreeMarkerTemplateEmail extends TemplateEmail {

    @Override
    public void processTemplate(String templatePath) {
        if (StringUtils.isEmpty(templatePath)) {
            return;
        }

        FreeMarkerTemplateProcessor fmtp = new FreeMarkerTemplateProcessor();
        fmtp.getModelMap().putAll(this.getTemplateData());
        fmtp.processTemplateIntoString("/common/email" + templatePath);

        this.setEmailCn(fmtp.getProcessResult());
    }

}
