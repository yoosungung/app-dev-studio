package kr.ac.jj.shared.application.common.email.model;

import java.util.HashMap;
import java.util.Map;

public abstract class TemplateEmail extends BaseEmail {

    private final Map<String, Object> templateData;

    public TemplateEmail() {
        this.templateData = new HashMap<String, Object>();
    }

    public Map<String, Object> getTemplateData() {
        return this.templateData;
    }

    public void setTemplateData(String key, Object value) {
        this.templateData.put(key, value);
    }

    public abstract void processTemplate(String templatePath);

}
