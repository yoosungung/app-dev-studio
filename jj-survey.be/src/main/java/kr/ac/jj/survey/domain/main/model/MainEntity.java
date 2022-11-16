package kr.ac.jj.survey.domain.main.model;

import kr.ac.jj.survey.infrastructure.framework.core.support.collection.BaseEntity;
import kr.ac.jj.survey.infrastructure.framework.web.support.codedata.CodeDataUtil;

public class MainEntity extends BaseEntity {
    private static final long serialVersionUID = -8620523699644126087L;

    protected String getBooleanCodeName(String codeDataPath, Boolean bool) {
        if (bool == null) {
            return null;
        }

        String boolStr = (bool ? "1" : "0");

        return CodeDataUtil.getCodeName(codeDataPath, boolStr, boolStr);
    }

    /*
     * public TbComFileList getTbComFileList(String fileGroupId) { if
     * (StringUtils.isEmpty(fileGroupId)) { return null; }
     * 
     * FileServiceImpl fileService =
     * ApplicationContextUtil.getBean(FileServiceImpl.class); TbComFileList
     * tbComFileList = fileService.selectList(fileGroupId);
     * 
     * return tbComFileList; }
     */
}
