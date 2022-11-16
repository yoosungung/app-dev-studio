package kr.ac.jj.survey.application.result.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.ResultContext;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.jj.shared.infrastructure.framework.core.foundation.exception.BaseException;
import kr.ac.jj.shared.infrastructure.framework.core.persistence.dao.helper.SqlHelper;
import kr.ac.jj.shared.infrastructure.framework.core.persistence.dao.helper.SqlHelper.SelectWrappingTypes;
import kr.ac.jj.shared.infrastructure.framework.core.persistence.dao.helper.SqlHelperUtil;
import kr.ac.jj.shared.infrastructure.framework.core.support.collection.ValueConverter;
import kr.ac.jj.shared.infrastructure.framework.core.support.context.ApplicationContextUtil;
import kr.ac.jj.shared.infrastructure.framework.core.support.io.util.IOUtil;
import kr.ac.jj.shared.infrastructure.framework.mybatis.persistence.dao.handler.DataResultHandler;
import kr.ac.jj.shared.infrastructure.grid.handler.GridDataResultHandler;
import kr.ac.jj.shared.infrastructure.grid.model.GridRequest;
import kr.ac.jj.survey.application.result.mapper.SurveyResultMapper;
import kr.ac.jj.survey.application.result.model.SurveyResultModel;
import kr.ac.jj.survey.application.result.model.SurveyResultProcessionLabel;
import kr.ac.jj.survey.application.result.model.SurveyResultQuestion;
import kr.ac.jj.survey.application.result.model.SurveyResultQuestionOption;
import kr.ac.jj.survey.domain.main.mapper.jd.survey.definition.JdSurveyDefinitionMapper;
import kr.ac.jj.survey.domain.main.mapper.survey.qestnr.TbSurveyQestnrMapper;
import kr.ac.jj.survey.domain.main.model.jd.survey.definition.JdSurveyDefinition;
import kr.ac.jj.survey.domain.main.model.survey.qestnr.TbSurveyQestnr;

/**
 * 설문 결과 Service
 */
@Service
public class SurveyResultServiceImpl {

    private @Autowired SurveyResultMapper surveyResultMapper;
    private @Autowired TbSurveyQestnrMapper tbSurveyQestnrMapper;
    private @Autowired JdSurveyDefinitionMapper jdSurveyDefinitionMapper;

    /**
     * 목록 조회
     *
     * @param gridRequest
     * @return
     */
    public List<Map<String, Object>> readList(GridRequest gridRequest) {
        GridDataResultHandler resultHandler = new GridDataResultHandler(gridRequest);

        surveyResultMapper.selectList(resultHandler, gridRequest.getPaging(), gridRequest.getSearch());

        return resultHandler.getResultList();
    }

    /**
     * 조회
     *
     * @param surveyQestnrId
     * @return
     */
    public SurveyResultModel read(String surveyQestnrId) {
        SurveyResultModel model = new SurveyResultModel();

        TbSurveyQestnr tbSurveyQestnr = tbSurveyQestnrMapper.select(surveyQestnrId);

        model.setTbSurveyQestnr(tbSurveyQestnr);
        model.setJdSurveyDefinition(jdSurveyDefinitionMapper.select(tbSurveyQestnr.getSurveyDefinitionId()));
        model.setDeviceSubmitList(surveyResultMapper.selectDeviceSubmitList(surveyQestnrId));
        model.setCmmnRspnsList(surveyResultMapper.selectCmmnRspnsList(surveyQestnrId));

        return model;
    }

    /**
     * 엑셀 생성
     *
     * @param surveyQestnrId
     * @param tempFile
     */
    public void createExcel(String surveyQestnrId, File tempFile) {
        Workbook workbook = new SXSSFWorkbook(100);

        TbSurveyQestnr tbSurveyQestnr = tbSurveyQestnrMapper.select(surveyQestnrId);
        JdSurveyDefinition jdSurveyDefinition = jdSurveyDefinitionMapper.select(tbSurveyQestnr.getSurveyDefinitionId());

        Map<String, SurveyResultQuestion> surveyQuestionMap = new HashMap<String, SurveyResultQuestion>();

        surveyResultMapper.selectSurveyQuestionList(tbSurveyQestnr.getSurveyDefinitionId(),
                new DataResultHandler<SurveyResultQuestion>() {

                    @Override
                    public void handleResult(ResultContext<? extends SurveyResultQuestion> resultContext) {
                        SurveyResultQuestion surveyResultQuestion = resultContext.getResultObject();
                        surveyQuestionMap.put(surveyResultQuestion.getQuestionKey(), surveyResultQuestion);
                    }

                });

        Map<String, SurveyResultQuestionOption> surveyQuestionOptionMap = new HashMap<String, SurveyResultQuestionOption>();

        surveyResultMapper.selectSurveyQuestionOptionList(tbSurveyQestnr.getSurveyDefinitionId(),
                new DataResultHandler<SurveyResultQuestionOption>() {

                    @Override
                    public void handleResult(ResultContext<? extends SurveyResultQuestionOption> resultContext) {
                        SurveyResultQuestionOption surveyResultQuestionOption = resultContext.getResultObject();
                        surveyQuestionOptionMap.put(surveyResultQuestionOption.getOptionKey(),
                                surveyResultQuestionOption);
                    }

                });

        Map<String, SurveyResultProcessionLabel> surveyResultProcessionLabelMap = new HashMap<String, SurveyResultProcessionLabel>();

        surveyResultMapper.selectSurveyProcessionLabelList(tbSurveyQestnr.getSurveyDefinitionId(),
                new DataResultHandler<SurveyResultProcessionLabel>() {

                    @Override
                    public void handleResult(ResultContext<? extends SurveyResultProcessionLabel> resultContext) {
                        SurveyResultProcessionLabel surveyResultProcessionLabel = resultContext.getResultObject();
                        surveyResultProcessionLabelMap.put(surveyResultProcessionLabel.getProcessionKey(),
                                surveyResultProcessionLabel);
                    }

                });

        List<String> columnNameList = this.readColumnNameList(surveyQestnrId,
                "SURVEY_DATA_" + tbSurveyQestnr.getSurveyDefinitionId(), surveyQuestionMap);
        Sheet sheet = this.createSheet(workbook, jdSurveyDefinition.getName(), columnNameList, surveyQuestionMap,
                surveyQuestionOptionMap, surveyResultProcessionLabelMap);

        CellStyle alignCenterCellStyle = workbook.createCellStyle();
        alignCenterCellStyle.setAlignment(HorizontalAlignment.CENTER);

        CellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setAlignment(HorizontalAlignment.CENTER);
        DataFormat dataFormat = workbook.createDataFormat();
        dateCellStyle.setDataFormat(dataFormat.getFormat("yyyy-MM-dd HH:mm:ss"));

        surveyResultMapper.selectSurveyDataList(tbSurveyQestnr.getSurveyDefinitionId(),
                new DataResultHandler<Map<String, Object>>() {

                    @Override
                    public void handleResult(ResultContext<? extends Map<String, Object>> resultContext) {
                        Map<String, Object> rowData = resultContext.getResultObject();
                        Row row = sheet.createRow(sheet.getPhysicalNumberOfRows());

                        {
                            Cell cell = row.createCell(0);
                            cell.setCellValue(ValueConverter.getDate(rowData.get("SUBMIT_DATE")));
                            cell.setCellStyle(dateCellStyle);
                        }

                        {
                            Cell cell = row.createCell(1);
                            cell.setCellValue(ValueConverter.getDouble(rowData.get("survey_id")));
                            cell.setCellStyle(alignCenterCellStyle);
                        }

                        for (int i = 0; i < columnNameList.size(); i++) {
                            String columnName = columnNameList.get(i);

                            Cell cell = row.createCell(row.getPhysicalNumberOfCells());

                            if (Pattern.matches("p[0-9]+q[0-9]+", columnName)) {
                                Object value = rowData.get(columnName);

                                if (value == null) {
                                    continue;
                                }

                                SurveyResultQuestionOption surveyResultQuestionOption = surveyQuestionOptionMap
                                        .get(columnName + "o" + value);

                                if (surveyResultQuestionOption != null) {
                                    cell.setCellValue(surveyResultQuestionOption.getOptionText());
                                } else {
                                    cell.setCellValue(value + "");
                                }
                            } else {
                                Object value = rowData.get(columnName);

                                if (value == null) {
                                    continue;
                                }

                                cell.setCellValue(value + "");
                            }
                        }
                    }

                });

        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(tempFile);
            workbook.write(fos);
        } catch (FileNotFoundException e) {
            tempFile.delete();
            throw new BaseException(e);
        } catch (IOException e) {
            tempFile.delete();
            throw new BaseException(e);
        } finally {
            IOUtil.closeQuietly(fos, workbook);
        }
    }

    private List<String> readColumnNameList(String surveyQestnrId, String tableNm,
            Map<String, SurveyResultQuestion> surveyQuestionMap) {

        List<String> columnNameList = surveyResultMapper.selectCmmnColumnList(surveyQestnrId);

        DataSource dataSource = ApplicationContextUtil.getBean("dataSource.main", DataSource.class);
        SqlHelper sqlHelper = SqlHelperUtil.getSqlHelper(dataSource);
        String sql = sqlHelper.getWrappedSelectSql("SELECT * FROM " + tableNm, SelectWrappingTypes.LIMIT_ZERO);

        try (Connection connection = dataSource.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            for (int i = 0; i < columnCount; i++) {
                String columnName = rsmd.getColumnName(i + 1);

                if (StringUtils.equals(columnName, "survey_id")) {
                    continue;
                }

                if (Pattern.matches("p[0-9]+v", columnName)) {
                    continue;
                }

                if (StringUtils.endsWith(columnName, "text")) {
                    String questionKey = StringUtils.substringBefore(columnName, "text");
                    SurveyResultQuestion surveyResultQuestion = surveyQuestionMap.get(questionKey);

                    if (surveyResultQuestion != null && surveyResultQuestion.getAllowOtherTextBox() != 1) {
                        continue;
                    }
                }

                columnNameList.add(columnName);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return columnNameList;
    }

    private Sheet createSheet(Workbook workbook, String sheetName, List<String> columnNameList,
            Map<String, SurveyResultQuestion> surveyQuestionMap,
            Map<String, SurveyResultQuestionOption> surveyQuestionOptionMap,
            Map<String, SurveyResultProcessionLabel> surveyResultProcessionLabelMap) {
        Sheet sheet = workbook.createSheet(sheetName);

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFFont headerFont = (XSSFFont) workbook.createFont();
        headerFont.setFontName("Arial");
        headerFont.setFontHeightInPoints((short) 9);
        headerFont.setBold(true);
        headerStyle.setFont(headerFont);

        sheet.setDisplayGridlines(false);
        sheet.createFreezePane(0, 1);

        Row row = sheet.createRow(0);
        row.setHeight((short) (15 * 20));

        {
            Cell cell = row.createCell(0);
            cell.setCellValue("응답일시");
            cell.setCellStyle(headerStyle);

            sheet.setColumnWidth(cell.getColumnIndex(), 140 * 35);
        }

        {
            Cell cell = row.createCell(1);
            cell.setCellValue("survey_id");
            cell.setCellStyle(headerStyle);

            sheet.setColumnWidth(cell.getColumnIndex(), 100 * 35);
        }

        for (int i = 0; i < columnNameList.size(); i++) {
            String columnName = columnNameList.get(i);

            Cell cell = row.createCell(row.getPhysicalNumberOfCells());

            Pattern pattern = Pattern.compile("(p[0-9]+q[0-9]+)(.*)");
            Matcher matcher = pattern.matcher(columnName);

            if (matcher.find()) {
                String group1 = matcher.group(1);
                String group2 = matcher.group(2);
                String cellValue1;

                if (surveyQuestionMap.get(group1) != null) {
                    cellValue1 = surveyQuestionMap.get(group1).getQuestionText();
                } else {
                    cellValue1 = group1;
                }

                if (StringUtils.isNotEmpty(group2)) {
                    SurveyResultProcessionLabel surveyResultProcessionLabel = surveyResultProcessionLabelMap
                            .get(columnName);

                    if (surveyResultProcessionLabel != null) {
                        cell.setCellValue(cellValue1 + " - " + surveyResultProcessionLabel.getProcessionLabel());
                    } else {
                        SurveyResultQuestionOption surveyResultQuestionOption = surveyQuestionOptionMap.get(columnName);

                        if (surveyResultQuestionOption != null) {
                            cell.setCellValue(cellValue1 + " - " + surveyResultQuestionOption.getOptionText());
                        } else {
                            cell.setCellValue(cellValue1 + " - " + group2);
                        }
                    }
                } else {
                    cell.setCellValue(cellValue1);
                }
            } else {
                String cellValue;

                if (surveyQuestionMap.get(columnName) != null) {
                    cellValue = surveyQuestionMap.get(columnName).getQuestionText();
                } else {
                    cellValue = columnName;
                }

                cell.setCellValue(cellValue);
            }

            cell.setCellStyle(headerStyle);
        }

        return sheet;
    }

}
