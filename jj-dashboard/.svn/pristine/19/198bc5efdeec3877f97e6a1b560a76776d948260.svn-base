package kr.ac.jj.dashboard.application.careerpath.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import kr.ac.jj.dashboard.application.careerpath.mapper.CareerPathMapper;
import kr.ac.jj.shared.infrastructure.util.ExcelUtil;

@Controller
@RequestMapping(path = "/careerpath/CareerPath")
public class CareerPathController {

    private static final Logger log = LoggerFactory.getLogger(CareerPathController.class);
    private @Autowired CareerPathMapper careerPathMapper;
    /**
     * 화면
     *
     * @return
     */
    @GetMapping
    public String view() {
        return "tiles2-common:/careerpath/CareerPathView";
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

                Map<String, Object> student = new HashMap<>();
                student.put("hakbun", ExcelUtil.getCellStringValue(row.getCell(0)));
                student.put("daehakName", ExcelUtil.getCellValue(row.getCell(1)));
                student.put("hakbuName", ExcelUtil.getCellValue(row.getCell(2)));
                student.put("irum", ExcelUtil.getCellValue(row.getCell(3)));
                student.put("remark", ExcelUtil.getCellValue(row.getCell(4)));
                student.put("emailAddr", ExcelUtil.getCellValue(row.getCell(5)));
                student.put("phoneNo", ExcelUtil.getCellStringValue(row.getCell(6)));
                student.put("jolGbn", ExcelUtil.getCellValue(row.getCell(7)));

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                student.put("employmentDate", sdf.parse(ExcelUtil.getCellStringValue(row.getCell(8))).getTime());

                student.put("graduatedYy", ExcelUtil.getCellStringValue(row.getCell(9)));
                student.put("managementDept", ExcelUtil.getCellValue(row.getCell(10)));
                student.put("managementPhone", ExcelUtil.getCellStringValue(row.getCell(11)));
                student.put("position", ExcelUtil.getCellValue(row.getCell(12)));

                careerPathMapper.create(student);
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
