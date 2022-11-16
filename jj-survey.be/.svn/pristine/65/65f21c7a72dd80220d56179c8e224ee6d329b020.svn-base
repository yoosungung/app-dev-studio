package kr.ac.jj.survey.infrastructure.framework.core.support.collection;

import org.apache.commons.lang3.StringUtils;

/**
 * 데이터 Job 처리 유형을 관리하는 클래스.
 */
public enum DataJobTypes {
    /**
     * Job 처리 유형 - 없음.
     */
    NORMAL("N"), //

    /**
     * Job 처리 유형 - 생성.
     */
    CREATE("C"), //

    /**
     * Job 처리 유형 - 수정.
     */
    UPDATE("U"), //

    /**
     * Job 처리 유형 - 삭제.
     */
    DELETE("D"), //
    ;

    private String shortValue;

    private DataJobTypes(String shortValue) {
        this.shortValue = shortValue;
    }

    public String getShortValue() {
        return this.shortValue;
    }

    public static DataJobTypes shortValueOf(String shortValue) {
        if (StringUtils.isEmpty(shortValue)) {
            return NORMAL;
        }

        DataJobTypes[] values = values();

        for (int i = 0; i < values.length; i++) {
            if (values[i].getShortValue().equals(shortValue)) {
                return values[i];
            }
        }

        return null;
    }
}
