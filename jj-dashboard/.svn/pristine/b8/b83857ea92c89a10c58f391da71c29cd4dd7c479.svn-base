package kr.ac.jj.dashboard.application.dropout.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

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

import kr.ac.jj.dashboard.application.careerpath.controller.CareerPathController;
import kr.ac.jj.dashboard.application.dropout.mapper.DropOutMapper;
import kr.ac.jj.shared.infrastructure.util.ExcelUtil;

@Controller
@RequestMapping(path = "/dropout/DropOut")
public class DropOutController {

    private static final Logger log = LoggerFactory.getLogger(CareerPathController.class);
    private @Autowired DropOutMapper dropOutMapper;
    /**
     * 중도탈락 화면
     *
     * @return
     */
    @GetMapping
    public String view() {
        return "tiles2-common:/dropout/DropOutView";
    }

    /**
     * 엑셀 업로드
     *
     */
    @PostMapping(path = "/uploadExcel")
    public @ResponseBody void uploadExcel(@RequestParam("upload") MultipartFile multipartFile) throws ParseException {
        Workbook workbook = null;

        try {
            workbook = new XSSFWorkbook(multipartFile.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                Row row = sheet.getRow(i);
                if (dropOutMapper.isDuplicate(ExcelUtil.getCellValue(row.getCell(1))) > 0) {
                    break;
                }
                Map<String, Object> student = new HashMap<>();
                student.put("ipsyName", ExcelUtil.getCellStringValue(row.getCell(0)));
                student.put("ipsyHakbun", ExcelUtil.getCellValue(row.getCell(1)));
                student.put("ipsyYear", ExcelUtil.getCellValue(row.getCell(2)));
                student.put("ipsySex", "남".equals(ExcelUtil.getCellValue(row.getCell(3))) ? "1" : "2");
                student.put("ipsyBirth", ExcelUtil.getCellStringValue(row.getCell(4)));
                student.put("ipsyDaehak", ExcelUtil.getCellValue(row.getCell(5)));
                student.put("ipsyHapgyHakkwaName", ExcelUtil.getCellValue(row.getCell(6)));
                student.put("schoNpostDong", ExcelUtil.getCellValue(row.getCell(7)));
                student.put("ipsyYy", ExcelUtil.getCellValue(row.getCell(8)));
                student.put("ipsyGubunName", ExcelUtil.getCellValue(row.getCell(9)));
                student.put("ihLclass", ExcelUtil.getCellValue(row.getCell(10)));
                student.put("ihName", ExcelUtil.getCellValue(row.getCell(11)));
                student.put("ipsyTotalJumsu1", ExcelUtil.getCellStringValue(row.getCell(12)));
                student.put("ipsyPaper21", ExcelUtil.getCellStringValue(row.getCell(13)));
                student.put("ipsyBongsaTime", ExcelUtil.getCellStringValue(row.getCell(14)));
                student.put("ipsyPaperAve", ExcelUtil.getCellStringValue(row.getCell(15)));
                student.put("ipsyHuboSeq", ExcelUtil.getCellStringValue(row.getCell(16)));
                student.put("ipsyKumjung", ExcelUtil.getCellStringValue(row.getCell(17)));
                student.put("ipsyHigh100", ExcelUtil.getCellStringValue(row.getCell(18)));
                student.put("ipsyGasanJumsu11", ExcelUtil.getCellStringValue(row.getCell(19)));
                student.put("ipsyGasanJumsu23", ExcelUtil.getCellStringValue(row.getCell(20)));
                student.put("ipsyGasanJumsu24", ExcelUtil.getCellStringValue(row.getCell(21)));
                student.put("ipsyMeunjubAve", ExcelUtil.getCellStringValue(row.getCell(22)));
                student.put("ipsyPaper11", ExcelUtil.getCellStringValue(row.getCell(23)));
                student.put("ipsyJangIphak", ExcelUtil.getCellStringValue(row.getCell(24)));
                student.put("ipsyJangSuup", ExcelUtil.getCellStringValue(row.getCell(25)));
                student.put("ipsyHighYy", ExcelUtil.getCellValue(row.getCell(26)));

                dropOutMapper.create(student);
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
