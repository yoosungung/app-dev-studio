package kr.ac.jj.survey.infrastructure.framework.web.multipart.nameformatter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import kr.ac.jj.survey.infrastructure.framework.core.support.sequence.BaseSequence;

public class UploadedFilenameUtil {
    private static final BaseSequence noSeq = new BaseSequence(1, 99999);

    private UploadedFilenameUtil() {
    }

    public static File newFile(File parent, String child) {
        if (parent == null || StringUtils.isEmpty(parent.getPath())) {
            return new File(child);
        }

        return new File(parent, child);
    }

    public static String getTimestampSequenceNo() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");

        return dateFormat.format(new Date()) + "-" + getSequenceNo();
    }

    public static String getSequenceNo() {
        return noSeq.getNextValue('0');
    }
}
