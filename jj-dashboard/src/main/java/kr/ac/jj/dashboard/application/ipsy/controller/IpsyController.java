package kr.ac.jj.dashboard.application.ipsy.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 입학 분석 데이터 Controller (1/2)
 *
 */
@Controller
@RequestMapping(path = "/ipsy/Ipsy1")
public class IpsyController {

    private @Autowired IpsyHeaderRestController ipsyHeaderRestController;

    /**
     * 화면 (1/2)
     *
     * @return
     */
    @GetMapping
    public String view(HttpServletRequest request, Model model, HttpServletResponse response) {
        Map<String, Object> result = ipsyHeaderRestController.readData();
        model.addAttribute("headerData", result);
        return "tiles2-common:/ipsy/IpsyView";
    }

}
