package kr.ac.jj.shared.application.admin.sysmanage.sysconfig.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 시스템 설정 Controller
 */
@Controller
@RequestMapping(path = "/admin/sysmanage/sysconfig/SystemConfig")
public class SystemConfigController {

    private @Autowired ReloadableResourceBundleMessageSource messageSource;

    /**
     * Refresh
     *
     * @return
     */
    @GetMapping(path = "/refresh")
    public @ResponseBody String refresh() {
        this.messageSource.clearCache();

        return "Refresh OK";
    }

}
