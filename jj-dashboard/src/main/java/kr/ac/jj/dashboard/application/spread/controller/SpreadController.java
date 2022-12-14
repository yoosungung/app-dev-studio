package kr.ac.jj.dashboard.application.spread.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.jj.dashboard.application.ipsy.controller.IpsyHeaderRestController;

/**
 * 출신 지역 분포 Controller
 *
 */
@Controller
@RequestMapping(path = "/spread/Spread")
public class SpreadController {

    private @Autowired IpsyHeaderRestController ipsyHeaderRestController;

    /**
     * 화면
     *
     * @return
     */
    @GetMapping
    public String view(HttpServletRequest request, Model model, HttpServletResponse response) {
        Map<String, Object> result = ipsyHeaderRestController.readData();
        model.addAttribute("headerData", result);
        return "tiles2-common:/spread/SpreadView";
    }

}
