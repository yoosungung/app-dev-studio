package kr.ac.jj.shared.domain.main.model;

import kr.ac.jj.shared.infrastructure.framework.core.support.collection.BaseEntity;
import kr.ac.jj.shared.infrastructure.framework.web.support.codedata.CodeDataUtil;

public class MainEntity extends BaseEntity {

    private static final long serialVersionUID = -8620523699644126087L;

    protected String getBooleanCodeName(String codeDataPath, Boolean bool) {
        if (bool == null) {
            return null;
        }

        String boolStr = Boolean.toString(bool);

        return CodeDataUtil.getCodeName(codeDataPath, boolStr, boolStr);
    }

    /*
    public TbComFileList getTbComFileList(String fileGroupId) {
        if (StringUtils.isEmpty(fileGroupId)) {
            return null;
        }

        FileServiceImpl fileService = ApplicationContextUtil.getBean(FileServiceImpl.class);
        TbComFileList tbComFileList = fileService.selectList(fileGroupId);

        return tbComFileList;
    }
    */

}
