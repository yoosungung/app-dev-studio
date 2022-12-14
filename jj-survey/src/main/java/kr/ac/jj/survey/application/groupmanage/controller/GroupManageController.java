package kr.ac.jj.survey.application.groupmanage.controller;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.ac.jj.shared.domain.main.mapper.com.person.TbComPersonMapper;
import kr.ac.jj.shared.domain.main.model.com.person.TbComPerson;
import kr.ac.jj.shared.infrastructure.util.ExcelUtil;
import kr.ac.jj.survey.application.groupmanage.service.GroupManageServiceImpl;

/**
 * 사람 관리 Controller
 */
@Controller
@RequestMapping(path = "/groupmanage/GroupManage")
public class GroupManageController {

    private static final Logger log = LoggerFactory.getLogger(GroupManageController.class);

    private @Autowired GroupManageServiceImpl groupManageService;
    private @Autowired TbComPersonMapper tbComPersonMapper;

    /**
     * 화면
     *
     * @return
     */
    @GetMapping
    public String view() {
        return "tiles2-common:/groupmanage/GroupManageView";
    }

    /**
     * 엑셀 업로드
     */
    @PostMapping(path = "/uploadExcel")
    public @ResponseBody void uploadExcel(@RequestParam("upload") MultipartFile multipartFile, @RequestParam String surveyGroupId) {
        Workbook workbook = null;

        try {
            workbook = new XSSFWorkbook(multipartFile.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                Row row = sheet.getRow(i);

                TbComPerson tbComPerson = new TbComPerson();
                tbComPerson.setKoreanNm(ExcelUtil.getCellValue(row.getCell(0)));
                tbComPerson.setEmailAdres(ExcelUtil.getCellValue(row.getCell(1)));
                tbComPerson.setTlphonNo(ExcelUtil.getCellValue(row.getCell(2)));

                String personId = tbComPersonMapper.selectPersonId(tbComPerson);

                if (StringUtils.isEmpty(personId)) {
                    tbComPerson.newId();
                    tbComPerson.setPersonSe(null);

                    tbComPersonMapper.insert(tbComPerson);

                    personId = tbComPerson.getPersonId();
                }

                // 사람(대상자) 추가
                groupManageService.createPerson(surveyGroupId, personId);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    log.error("Excel workbook close error.", e);
                }
            }
        }
    }

}
