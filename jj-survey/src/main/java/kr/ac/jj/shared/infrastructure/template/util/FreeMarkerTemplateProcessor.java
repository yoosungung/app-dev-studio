package kr.ac.jj.shared.infrastructure.template.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import kr.ac.jj.shared.infrastructure.framework.core.foundation.exception.BaseException;
import kr.ac.jj.shared.infrastructure.framework.core.support.context.ApplicationContextUtil;

public class FreeMarkerTemplateProcessor {

    private final Map<String, Object> modelMap;

    private String processResult;

    public FreeMarkerTemplateProcessor() {
        this.modelMap = new HashMap<String, Object>();
    }

    public Map<String, Object> getModelMap() {
        return this.modelMap;
    }

    public void setModel(String key, Object value) {
        this.modelMap.put(key, value);
    }

    public String processTemplateIntoString(String templatePath) {
        if (StringUtils.isEmpty(templatePath)) {
            return null;
        }

        Configuration freeMarkerConfiguration = ApplicationContextUtil.getBean(Configuration.class);

        Template template;

        try {
            template = freeMarkerConfiguration.getTemplate(templatePath);
        } catch (TemplateNotFoundException e) {
            throw new BaseException(e);
        } catch (MalformedTemplateNameException e) {
            throw new BaseException(e);
        } catch (ParseException e) {
            throw new BaseException(e);
        } catch (IOException e) {
            throw new BaseException(e);
        }

        try {
            this.processResult = FreeMarkerTemplateUtils.processTemplateIntoString(template, this.getModelMap());
            return this.processResult;
        } catch (TemplateException e) {
            throw new BaseException(e);
        } catch (IOException e) {
            throw new BaseException(e);
        }
    }

    public String getProcessResult() {
        return this.processResult;
    }

}
