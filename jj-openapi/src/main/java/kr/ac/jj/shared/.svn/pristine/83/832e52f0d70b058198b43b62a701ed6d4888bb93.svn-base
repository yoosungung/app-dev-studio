package kr.ac.jj.shared.application.common.menu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.jj.shared.application.common.menu.service.MenuLoadServiceImpl;
import kr.ac.jj.shared.infrastructure.framework.core.support.collection.BaseMapList;

/**
 * 메뉴 로딩 RestController
 */
@RestController
@RequestMapping(value = "/common/menu/MenuLoad")
public class MenuLoadRestController {

    private @Autowired MenuLoadServiceImpl menuLoadService;

    /**
     * 전체 목록 조회
     *
     * @param menuKnd
     * @return
     */
    @GetMapping(value = "/{menuKnd}/readAllList.do")
    public BaseMapList readAllList(@PathVariable String menuKnd) {
        return menuLoadService.readAllList(menuKnd);
    }

}
