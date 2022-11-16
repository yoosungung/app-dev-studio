package kr.ac.jj.shared.infrastructure.framework.core.support.codedata;

import java.util.ArrayList;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

public class CodeDataBooleanTypeList extends ArrayList<CodeDataBooleanType> {

    private static final long serialVersionUID = 7709224841369650118L;

    private String[] trueCodes;
    private String[] falseCodes;

    @Override
    public boolean add(CodeDataBooleanType codeDataBooleanType) {
        boolean result = super.add(codeDataBooleanType);

        if (this.trueCodes == null || this.falseCodes == null) {
            this.trueCodes = new String[] { codeDataBooleanType.getTrueCode() };
            this.falseCodes = new String[] { codeDataBooleanType.getFalseCode() };
        } else {
            this.trueCodes = ArrayUtils.add(this.trueCodes, codeDataBooleanType.getTrueCode());
            this.falseCodes = ArrayUtils.add(this.falseCodes, codeDataBooleanType.getFalseCode());
        }

        return result;
    }

    public boolean isBooleanType(String code1, String code2) {
        for (CodeDataBooleanType booleanCode : this) {
            String trueCode = booleanCode.getTrueCode();
            String falseCode = booleanCode.getFalseCode();

            if (StringUtils.equalsIgnoreCase(code1, trueCode) && StringUtils.equalsIgnoreCase(code2, falseCode)) {
                return true;
            }

            if (StringUtils.equalsIgnoreCase(code1, falseCode) && StringUtils.equalsIgnoreCase(code2, trueCode)) {
                return true;
            }
        }

        return false;
    }

    public boolean isTrueCode(String code) {
        return StringUtils.equalsAnyIgnoreCase(code, this.trueCodes);
    }

    public boolean isFalseCode(String code) {
        return StringUtils.equalsAnyIgnoreCase(code, this.falseCodes);
    }

}
