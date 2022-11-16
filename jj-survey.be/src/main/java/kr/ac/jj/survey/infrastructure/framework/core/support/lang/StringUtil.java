package kr.ac.jj.survey.infrastructure.framework.core.support.lang;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import kr.ac.jj.survey.infrastructure.framework.core.foundation.exception.BaseException;

/**
 * 문자열 관련 유틸리티.
 */
public class StringUtil {
    private StringUtil() {
    }

    /**
     * 특정 문자열을 HTML 형식으로 Escape 처리하여 반환.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) StringUtil.toHTML("<xmp><script></xmp>");
     * </pre>
     *
     * @param text 변환할 문자
     * @return 반환예 : <xmp>&lt;script&gt;</xmp>
     */
    public static String toHTML(String text) {
        return toHTML(text, null, false);
    }

    /**
     * 특정 문자열을 HTML 형식으로 Escape 처리하여 반환.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) StringUtil.toHTML("<xmp><script></xmp>");
     * </pre>
     *
     * @param text         변환할 문자
     * @param defaultValue 기본값
     * @return 반환예 : <xmp>&lt;script&gt;</xmp>
     */
    public static String toHTML(String text, String defaultValue) {
        return toHTML(text, defaultValue, false);
    }

    /**
     * 특정 문자열을 HTML 형식으로 Escape 처리하여 반환.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) StringUtil.toHTML("<xmp><script></xmp>", true);
     * </pre>
     *
     * @param text  변환할 문자
     * @param isPre <code><xmp><pre></xmp></code> 태그 안에 입력되는 문자열로 처리하려는 경우 true로 설정
     * @return 반환예 : <xmp>&lt;script&gt;</xmp>
     */
    public static String toHTML(String text, boolean isPre) {
        return toHTML(text, null, isPre);
    }

    /**
     * 특정 문자열을 HTML 형식으로 Escape 처리하여 반환.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) StringUtil.toHTML("<xmp><script></xmp>", true);
     * </pre>
     *
     * @param text         변환할 문자
     * @param defaultValue 기본값
     * @param isPre        <code><xmp><pre></xmp></code> 태그 안에 입력되는 문자열로 처리하려는 경우
     *                     true로 설정
     * @return 반환예 : <xmp>&lt;script&gt;</xmp>
     */
    public static String toHTML(String text, String defaultValue, boolean isPre) {
        if (StringUtils.isEmpty(text)) {
            return nvl(defaultValue, "");
        }

        int len = text.length();

        StringBuilder result = new StringBuilder(len);

        for (int i = 0; i < len; i++) {
            char chr = text.charAt(i);
            switch (chr) {
            case '<':
                result.append("&lt;");
                break;
            case '>':
                result.append("&gt;");
                break;
            case '"':
                result.append("&quot;");
                break;
            case '\'':
                result.append("&#39;");
                break;
            case '%':
                result.append("&#37;");
                break;
            case ';':
                result.append("&#59;");
                break;
            case '(':
                result.append("&#40;");
                break;
            case ')':
                result.append("&#41;");
                break;
            case '&':
                result.append("&amp;");
                break;
            case '+':
                result.append("&#43;");
                break;
            default:
                if (isPre) {
                    result.append(chr);
                    break;
                }
                switch (chr) {
                case ' ':
                    if (i + 1 < len && text.charAt(i + 1) == ' ') {
                        result.append("&nbsp;");
                    } else {
                        result.append(' ');
                    }
                    break;
                case '\r':
                    if (i + 1 < len && text.charAt(i + 1) == '\n') {
                        result.append("<br>\r\n");
                    } else {
                        result.append("<br>\r");
                    }
                    break;
                case '\n':
                    if (!(i > 0 && text.charAt(i - 1) == '\r')) {
                        result.append("<br>\n");
                    }
                    break;
                default:
                    result.append(chr);
                    break;
                }
                break;
            }
        }

        return result.toString();
    }

    /**
     * 특정 문자열을 XML 형식으로 Escape 처리하여 반환.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * <xmp>1) StringUtil.toHTML("<xml>xml</xml>");</xmp>
     * </pre>
     *
     * @param text 변환할 문자
     * @return 반환예 : <xmp><xml>xml</xml></xmp>
     */
    public static String toXML(String text) {
        if (text == null) {
            return "";
        }

        int len = text.length();

        StringBuilder result = new StringBuilder(len);

        for (int i = 0; i < len; i++) {
            char chr = text.charAt(i);
            switch (chr) {
            case '<':
                result.append("&lt;");
                break;
            case '>':
                result.append("&gt;");
                break;
            case '&':
                result.append("&amp;");
                break;
            case '\'':
                result.append("&apos;");
                break;
            case '"':
                result.append("&quot;");
                break;
            default:
                result.append(chr);
                break;
            }
        }

        return result.toString();
    }

    /**
     * 특정 문자열을 JSON 형식으로 Escape 처리하여 반환.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) StringUtil.toJS("var i");
     * </pre>
     *
     * @param text 변환할 문자
     * @return 반환예 : <xmp>var i</xmp>
     */
    public static String toJSON(String text) {
        if (text == null) {
            return "";
        }

        int len = text.length();

        StringBuffer result = new StringBuffer(len);

        for (int i = 0; i < len; i++) {
            char chr = text.charAt(i);
            switch (chr) {
            case '\\':
                result.append("\\\\");
                break;
            case '\r':
                result.append("\\r");
                break;
            case '\n':
                result.append("\\n");
                break;
            case '"':
                result.append("\\\"");
                break;
            case '<':
                result.append("\\074");
                break;
            case '>':
                result.append("\\076");
                break;
            default:
                result.append(chr);
                break;
            }
        }

        return result.toString();
    }

    /**
     * 특정 문자열을 JS 형식으로 Escape 처리하여 반환.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) StringUtil.toJS("var i");
     * </pre>
     *
     * @param text 변환할 문자
     * @return 반환예 : <xmp>var i</xmp>
     */
    public static String toJS(String text) {
        if (text == null) {
            return "";
        }

        int len = text.length();

        StringBuilder result = new StringBuilder(len);

        for (int i = 0; i < len; i++) {
            char chr = text.charAt(i);
            switch (chr) {
            case '\\':
                result.append("\\\\");
                break;
            case '\r':
                result.append("\\r");
                break;
            case '\n':
                result.append("\\n");
                break;
            case '"':
                result.append("\\042");
                break;
            case '\'':
                result.append("\\047");
                break;
            case '<':
                result.append("\\074");
                break;
            case '>':
                result.append("\\076");
                break;
            default:
                result.append(chr);
                break;
            }
        }

        return result.toString();
    }

    /**
     * 특정 문자열을 VBS 형식으로 Escape 처리하여 반환.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) StringUtil.toVBS("vbs");
     * </pre>
     *
     * @param text 변환할 문자
     * @return
     */
    public static String toVBS(String text) {
        if (text == null) {
            return "";
        }

        int len = text.length();

        StringBuilder result = new StringBuilder(len);

        for (int i = 0; i < len; i++) {
            char chr = text.charAt(i);
            switch (chr) {
            case '"':
                result.append("\"\"");
                break;
            case '<':
                if (i + 1 < len && text.charAt(i + 1) == '/') {
                    result.append("<\"+\"");
                } else {
                    result.append(chr);
                }
                break;
            case '\r':
                if (i + 1 < len && text.charAt(i + 1) == '\n') {
                    result.append("\"&vbCrLf&\"");
                } else {
                    result.append("\"&vbCr&\"");
                }
                break;
            case '\n':
                if (!(i > 0 && text.charAt(i - 1) == '\r')) {
                    result.append("\"&vbLf&\"");
                }
                break;
            default:
                result.append(chr);
                break;
            }
        }

        return result.toString();
    }

    /**
     * Pascal Case로 변환.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) StringUtil.toPascalCase("text1");
     * </pre>
     *
     * @param text 변환할 문자
     * @return 반환예 : <xmp>Text1</xmp>
     */
    public static String toPascalCase(String text) {
        text = toCamelCase(text);

        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }

    /**
     * Camel Case로 변환.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) StringUtil.toPascalCase("textcase1");
     * </pre>
     *
     * @param text 변환할 문자
     * @return 반환예 : <xmp>textCase1</xmp>
     */
    public static String toCamelCase(String text) {
        if (StringUtils.isEmpty(text)) {
            return "";
        }

        if (text.startsWith("_")) {
            return text;
        }

        if (Character.isLowerCase(text.charAt(0)) && text.indexOf('_') == -1) {
            return text;
        }

        int len = text.length();
        StringBuilder sb = new StringBuilder(len);
        boolean isMustCapitalize = false;
        String str = text.toLowerCase();

        for (int i = 0; i < len; i++) {
            char chr = str.charAt(i);
            if (chr == '_' || chr == '-' || chr == ' ') {
                isMustCapitalize = true;
            } else if (chr >= '0' && chr <= '9') {
                sb.append(chr);
                isMustCapitalize = false;
            } else if (chr < 'a' || chr > 'z') {
                sb.append(chr);
                isMustCapitalize = true;
            } else if (isMustCapitalize) {
                sb.append((char) (chr - 0x20));
                isMustCapitalize = false;
            } else {
                sb.append(chr);
            }
        }

        return sb.toString();
    }

    /**
     * Snake Case로 변환.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) StringUtil.toSnakeCase("textCase1");
     * </pre>
     *
     * @param text 변환할 문자
     * @return 반환예 : <xmp>text_case1</xmp>
     */
    public static String toSnakeCase(String text) {
        return toSeparatorCase(text, '_');
    }

    /**
     * Kebab Case로 변환.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) StringUtil.toKebabCase("textCase1");
     * </pre>
     *
     * @param text 변환할 문자
     * @return 반환예 : <xmp>text-case1</xmp>
     */
    public static String toKebabCase(String text) {
        return toSeparatorCase(text, '-');
    }

    private static String toSeparatorCase(String text, char separator) {
        if (StringUtils.isEmpty(text)) {
            return "";
        }

        if (text.equals(text.toUpperCase())) {
            text = toCamelCase(text);
        }

        StringBuilder sb = new StringBuilder();
        char[] chrs = text.toCharArray();

        for (int i = 0, ii = chrs.length; i < ii; i++) {
            if (Character.isUpperCase(chrs[i])) {
                if (i > 0 && chrs[i - 1] != separator) {
                    sb.append(separator);
                }
            }
            sb.append(Character.toLowerCase(chrs[i]));
        }

        return sb.toString();
    }

    /**
     * 문자 찾기.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) StringUtil.isMatches("textCase", "*Case*");
     * </pre>
     *
     * @param text    대상 문자
     * @param pattern 찾을 패턴
     * @return
     */
    public static boolean isMatches(String text, String pattern) {
        return text.matches(pattern.replaceAll("\\*", "([^ \\\\t\\\\n\\\\r\\\\f\\\\v]*)"));
    }

    /**
     * 문자 Charset 확인.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) StringUtil.isCharset("text", "UTF-8");
     * </pre>
     *
     * @param chr         대상 문자
     * @param charsetName 확인할 charset
     * @return
     */
    public static boolean isCharset(char chr, String charsetName) {
        return isCharset(chr + "", charsetName);
    }

    /**
     * 문자 Charset 확인.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) StringUtil.isCharset("text", "UTF-8");
     * </pre>
     *
     * @param text        확인할 문자
     * @param charsetName 확인할 charset
     * @return
     */
    public static boolean isCharset(String text, String charsetName) {
        if (text == null) {
            return true;
        }

        try {
            return new String(text.getBytes(charsetName), charsetName).equals(text);
        } catch (UnsupportedEncodingException e) {
            throw new BaseException(e);
        }
    }

    /**
     * Charset Encoding.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) StringUtil.isCharset("text", "UTF-8");
     * </pre>
     *
     * @param text        변환할 문자
     * @param charsetName 변환할 Charset
     * @return
     */
    public static String charEncode(String text, String charsetName) {
        if (text == null) {
            return "";
        }

        Matcher m = Pattern.compile("&#([0-9]+);").matcher(text);

        StringBuffer sb1 = new StringBuffer(text.length());

        while (m.find()) {
            m.appendReplacement(sb1, m.group(0).replaceFirst("&", "&#" + ((int) '&') + ";"));
        }

        m.appendTail(sb1);

        text = sb1.toString();

        StringBuilder sb2 = new StringBuilder(text.length());

        for (int i = 0, ii = text.length(); i < ii; i++) {
            char chr = text.charAt(i);
            if (isCharset(chr, charsetName)) {
                sb2.append(chr);
            } else {
                sb2.append("&#" + ((int) chr) + ";");
            }
        }

        return sb2.toString();
    }

    /**
     * Charset Decoding.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) StringUtil.charDecode("text");
     * </pre>
     *
     * @param text 변환할 문자
     * @return
     */
    public static String charDecode(String text) {
        if (text == null) {
            return "";
        }

        Matcher m = Pattern.compile("&#([0-9]+);").matcher(text);

        StringBuffer sb = new StringBuffer();

        while (m.find()) {
            m.appendReplacement(sb, ((char) Integer.parseInt(m.group(1), 10)) + "");
        }

        m.appendTail(sb);

        return sb.toString();
    }

    /**
     * 케릭터 셋 변경.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) StringUtil.convertCharset("text");
     * </pre>
     *
     * @param text 변환할 문자
     * @return
     */
    public static String convertCharset(String text) {
        return convertCharset(text, null, "utf-8");
    }

    /**
     * 케릭터 셋 변경.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) StringUtil.convertCharset("text", "EUC-KR", "UTF-8");
     * </pre>
     *
     * @param text          변환할 문자
     * @param sourceCharset 현재 Charset
     * @param targetCharset 변환할 Charset
     * @return
     */
    public static String convertCharset(String text, String sourceCharset, String targetCharset) {
        if (text == null) {
            return null;
        }

        try {
            return new String(text.getBytes(nvl(sourceCharset, "ISO-8859-1")), nvl(targetCharset, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            throw new BaseException(e);
        }
    }

    /**
     * Replace Dollar Mark.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) StringUtil.replaceDollarMark("text");
     * </pre>
     *
     * @param text 변환할 문자
     * @return
     */
    public static String replaceDollarMark(String text) {
        if (text == null) {
            return "";
        }

        int len = text.length();

        StringBuilder result = new StringBuilder(len);

        for (int i = 0; i < len; i++) {
            char chr = text.charAt(i);
            if (chr == '$') {
                result.append("\\$");
            } else {
                result.append(chr);
            }
        }

        return result.toString();
    }

    /**
     * Trailing Spaces Remove.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) StringUtil.removeTrailingSpaces("text");
     * </pre>
     *
     * @param text 변환할 문자
     * @return
     */
    public static String removeTrailingSpaces(String text) {
        if (text == null) {
            return "";
        }

        text = text.replaceAll("[ \\t\\v]+\\r\\n", "\r\n");
        text = text.replaceAll("[ \\t\\v]+\\r", "\r");
        text = text.replaceAll("[ \\t\\v]+\\n", "\n");
        text = text.replaceAll("[ \\t\\v]+\\f", "\f");
        text = text.replaceAll("[ \\t\\v]+$", "");

        return text;
    }

    /**
     * Blank Lines Remove.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) StringUtil.removeBlankLines("text");
     * </pre>
     *
     * @param text 변환할 문자
     * @return
     */
    public static String removeBlankLines(String text) {
        if (text == null) {
            return "";
        }

        text = trimLineSeparator(text);

        while (text.indexOf("\r\n\r\n") != -1) {
            text = text.replaceAll("(\\r\\n){2}", "\r\n");
        }
        while (text.indexOf("\r\r") != -1) {
            text = text.replaceAll("(\\r){2}", "\r");
        }
        while (text.indexOf("\n\n") != -1) {
            text = text.replaceAll("(\\n){2}", "\r");
        }

        return text;
    }

    /**
     * Line Separator Trim.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) StringUtil.trimLineSeparator("text");
     * </pre>
     *
     * @param text 변환할 문자
     * @return
     */
    public static String trimLineSeparator(String text) {
        if (text == null) {
            return "";
        }

        text = text.replaceAll("^[\\r\\n]+|[\\r\\n]+$", "");

        return text;
    }

    /**
     * 길이만큼 text반복 출력.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) StringUtil.StringUtil.string("text", 13);
     * </pre>
     *
     * @param text   변환할 문자
     * @param length 길이
     * @return
     */
    public static String string(String text, int length) {
        StringBuilder sb = new StringBuilder();

        while (sb.length() < length) {
            sb.append(text);
        }

        return sb.toString();
    }

    /**
     * 왼쪽에 길이만큼 채울 문자 추가하여 출력.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) StringUtil.StringUtil.string("text", 13, "5"); => 555555555text
     * </pre>
     *
     * @param text   변환할 문자
     * @param length 길이
     * @param pad    채울 문자
     * @return
     */
    public static String lpad(String text, int length, String pad) {
        return lpad(text, length, pad, false);
    }

    /**
     * 왼쪽에 길이만큼 채울 문자 추가하여 출력.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) StringUtil.lpad("text", 3, "5", true); => tex
     * </pre>
     *
     * @param text     변환할 문자
     * @param length   길이
     * @param pad      채울 문자
     * @param truncate 길이 만큼만 출력여부 (default:false)
     * @return
     */
    public static String lpad(String text, int length, String pad, boolean truncate) {
        if (text == null) {
            return null;
        }

        if (truncate && text.length() >= length) {
            return text.substring(0, length);
        }

        StringBuilder sb = new StringBuilder(text);

        while (sb.length() < length) {
            sb.insert(0, pad);
        }

        return sb.toString();
    }

    /**
     * 오른쪽에 길이만큼 채울 문자 추가하여 출력.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) StringUtil.rpad("text", 13, "5"); => text555555555
     * </pre>
     *
     * @param text   변환할 문자
     * @param length 길이
     * @param pad    채울 문자
     * @return
     */
    public static String rpad(String text, int length, String pad) {
        return rpad(text, length, pad, false);
    }

    /**
     * 오른쪽에 길이만큼 채울 문자 추가하여 출력.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) StringUtil.rpad("text", 3, "5", true); = >tex
     * </pre>
     *
     * @param text     변환할 문자
     * @param length   길이
     * @param pad      채울 문자
     * @param truncate 길이 만큼만 출력여부 (default:false)
     * @return
     */
    public static String rpad(String text, int length, String pad, boolean truncate) {
        if (text == null) {
            return null;
        }

        if (truncate && text.length() >= length) {
            return text.substring(0, length);
        }

        StringBuilder sb = new StringBuilder(text);

        while (sb.length() < length) {
            sb.append(pad);
        }

        return sb.toString();
    }

    /**
     * 첫번째 인자가 null이 아닌 경우 첫번째 인자값 반환, null인 경우 두번째 인자값 반환.<br>
     * (오라클 NVL 함수와 동일)
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) StringUtil.nvl("text1", "value1"); => text1
     * 2) StringUtil.nvl(null, "value1");    => value1
     * </pre>
     *
     * @param text      비교할 문자열
     * @param nullValue null인 경우 반환할 문자열
     * @return
     */
    public static String nvl(String text, String nullValue) {
        return text == null ? nullValue : text;
    }

    /**
     * 첫번째 인자가 null이 아닌 경우 두번째 인자값 반환, null인 경우 세번째 인자값 반환.<br>
     * (오라클 NVL2 함수와 동일)
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) StringUtil.nvl2("text", "case", "test"); => text1
     * 2) StringUtil.nvl2("", "case", "test");     => case
     * </pre>
     *
     * @param text         변환할 문자
     * @param notNullValue null일 아닐 경우 바꿀 문자
     * @param nullValue    null일 경우 바꿀 문자
     * @return
     */
    public static String nvl2(String text, String notNullValue, String nullValue) {
        return text == null ? nullValue : notNullValue;
    }

    /**
     * 공백이 있는지 없는지 확인.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) StringUtil.isEmpty(null);      => true
     * 2) StringUtil.isEmpty("");        => true
     * 3) StringUtil.isEmpty(" ");       => false
     * 4) StringUtil.isEmpty("text");    => false
     * </pre>
     *
     * @param text 변환할 문자
     * @return
     */
    public static boolean isEmpty(String text) {
        return text == null || "".equals(text);
    }

    /**
     * 파라미터를 trim하여 공백이 있는지 없는지 확인.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) StringUtil.isBlank(null);      => true
     * 2) StringUtil.isBlank("");        => true
     * 3) StringUtil.isBlank(" ");       => true
     * 4) StringUtil.isBlank("text");    => false
     * </pre>
     *
     * @param text 변환할 문자
     * @return
     */
    public static boolean isBlank(String text) {
        return isEmpty(text) || "".equals(text.trim());
    }

    /**
     * 첫번째 인자가 null이 아닌 경우나 ""이 아닌 경우 첫번째 인자값 반환, null인 경우나 ""인 경우 두번째 인자값 반환.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) StringUtil.empty(null, "value");       => "value"
     * 2) StringUtil.empty("", "value");         => "value"
     * 3) StringUtil.empty(" ", "value");        => " "
     * 4) StringUtil.empty("text", "value");     => "text"
     * </pre>
     *
     * @param text       변환할 문자
     * @param emptyValue null이거나 ""일 경우 바꿀 문자
     * @return
     */
    public static String empty(String text, String emptyValue) {
        return isEmpty(text) ? emptyValue : text;
    }

    /**
     * 첫번째 인자가 null이 아닌 경우나 ""이 아닌 경우 두번째 인자값 반환, null인 경우나 ""인 경우 세번째 인자값 반환.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) StringUtil.empty2(null, "value1", "value2");       => "value2"
     * 2) StringUtil.empty2("", "value1", "value2");         => "value2"
     * 3) StringUtil.empty2(" ", "value1", "value2");        => "value1"
     * 4) StringUtil.empty2("text", "value1", "value2");     => "value1"
     * </pre>
     *
     * @param text          변환할 문자
     * @param notEmptyValue null이거 아니거나 ""이 아닐 경우 바꿀 문자
     * @param emptyValue    null이거나 ""일 경우 바꿀 문자
     * @return
     */
    public static String empty2(String text, String notEmptyValue, String emptyValue) {
        return isEmpty(text) ? emptyValue : notEmptyValue;
    }

    /**
     * 첫번째 인자가 trim(공백제거) 후 null이 아닌 경우 첫번째 인자값 반환, 첫번째 인자가 trim(공백제거) 후 null인 경우
     * 두번째 인자값 반환.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) StringUtil.blank(null, "value");       => "value"
     * 2) StringUtil.blank("", "value");         => "value"
     * 3) StringUtil.blank(" ", "value");        => "value"
     * 4) StringUtil.blank("text", "value");     => "text"
     * </pre>
     *
     * @param text       변환할 문자
     * @param blankValue trim된 문자가 ""일 경우 바꿀 문자
     * @return
     */
    public static String blank(String text, String blankValue) {
        return isBlank(text) ? blankValue : text;
    }

    /**
     * 첫번째 인자가 trim(공백제거) 후 null이 아닌 경우 두번째 인자값 반환, 첫번째 인자가 trim(공백제거) 후 null인 경우
     * 세번째 인자값 반환.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) StringUtil.blank2(null, "value1", "value2");       => "value2"
     * 2) StringUtil.blank2("", "value1", "value2");         => "value2"
     * 3) StringUtil.blank2(" ", "value1", "value2");        => "value2"
     * 4) StringUtil.blank2("text", "value1", "value2");     => "value1"
     * </pre>
     *
     * @param text          변환할 문자
     * @param notBlankValue trim된 문자가 ""이 아닐 경우 바꿀 문자
     * @param blankValue    trim된 문자가 ""일 경우 바꿀 문자
     * @return
     */
    public static String blank2(String text, String notBlankValue, String blankValue) {
        return isBlank(text) ? blankValue : notBlankValue;
    }

    /**
     * 문자 사이 값 출력.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) StringUtil.crop("text", "t", "x"); => e
     * </pre>
     *
     * @param text   변환할 문자
     * @param prefix 시작할 문자
     * @param suffix 종료할 문자
     * @return
     */
    public static String crop(String text, String prefix, String suffix) {
        if (text == null) {
            return null;
        }

        String result;

        if (prefix == null) {
            result = text;
        } else {
            int prefixIndex = text.indexOf(prefix);
            if (prefixIndex == -1) {
                return null;
            }
            result = text.substring(prefixIndex + prefix.length());
        }

        if (suffix != null) {
            int suffixIndex = result.indexOf(suffix);
            if (suffixIndex == -1) {
                return null;
            }
            return result.substring(0, suffixIndex);
        }

        return result;
    }

    /**
     * 문자 사이 값 출력.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) StringUtil.cropIgnoreCase("text", "t", "x");
     * </pre>
     *
     * @param text   변환할 문자
     * @param prefix 시작할 문자
     * @param suffix 종료할 문자
     * @return
     */
    public static String cropIgnoreCase(String text, String prefix, String suffix) {
        if (text == null) {
            return null;
        }

        String result;

        if (prefix == null) {
            result = text;
        } else {
            int prefixIndex = text.toUpperCase().indexOf(prefix.toUpperCase());
            if (prefixIndex == -1) {
                return null;
            }
            result = text.substring(prefixIndex + prefix.length());
        }

        if (suffix != null) {
            int suffixIndex = result.toUpperCase().indexOf(suffix.toUpperCase());
            if (suffixIndex == -1) {
                return null;
            }
            return result.substring(0, suffixIndex);
        }

        return result;
    }

    /**
     * String 포멧 바꾸기.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) StringUtil.format("10000", "##,##"); => 100,00
     * </pre>
     *
     * @param text   변환할 문자
     * @param format 바꿀 형식
     * @return
     */
    public static String format(String text, String format) {
        return format(text, format, '#', false);
    }

    /**
     * String 포멧 바꾸기.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) StringUtil.format("10000", "##,##", "#"); => 100,00
     * </pre>
     *
     * @param text   변환할 문자
     * @param format 바꿀 형식
     * @param symbol 형식으로 만든 기호 (default:#)
     * @return
     */
    public static String format(String text, String format, String symbol) {
        return format(text, format, symbol.charAt(0), false);
    }

    /**
     * String 포멧 바꾸기.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) StringUtil.format("10000", "##,##", "#"); => 100,00
     * </pre>
     *
     * @param text   변환할 문자
     * @param format 바꿀 형식
     * @param symbol 형식으로 만든 기호 (default:#)
     * @return
     */
    public static String format(String text, String format, char symbol) {
        return format(text, format, symbol, false);
    }

    /**
     * String 포멧 바꾸기.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) StringUtil.format("10000", "##,##", true); => 100,00
     * </pre>
     *
     * @param text    변환할 문자
     * @param format  바꿀 형식
     * @param exactly 형식 외에 문자 제거 유무 (default:false)
     * @return
     */
    public static String format(String text, String format, boolean exactly) {
        return format(text, format, '#', exactly);
    }

    /**
     * String 포멧 바꾸기.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * StringUtil.format(&quot;10000&quot;, &quot;##,##&quot;, &quot;#&quot;, true); => 100,00
     * </pre>
     *
     * @param text    변환할 문자
     * @param format  바꿀 형식
     * @param symbol  형식으로 만든 기호 (default:#)
     * @param exactly 형식 외에 문자 제거 유무 (default:false)
     * @return
     */
    public static String format(String text, String format, String symbol, boolean exactly) {
        return format(text, format, symbol.charAt(0), exactly);
    }

    /**
     * String 포멧 바꾸기.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) StringUtil.format("10000", "##,##", "#", true); => 100,00
     * </pre>
     *
     * @param text    변환할 문자
     * @param format  바꿀 형식
     * @param symbol  형식으로 만든 기호 (default:#)
     * @param exactly 형식 외에 문자 제거 유무 (default:false)
     * @return
     */
    public static String format(String text, String format, char symbol, boolean exactly) {
        if (StringUtils.isEmpty(text)) {
            return text;
        }
        if (StringUtils.isEmpty(format)) {
            return text;
        }

        StringBuilder result = new StringBuilder();
        int txtIdx = 0;

        for (int i = 0, ii = format.length(); i < ii; i++) {
            if (text.length() <= txtIdx) {
                break;
            }
            if (format.charAt(i) == symbol) {
                result.append(text.charAt(txtIdx++));
            } else if (text.length() > i && text.charAt(i) == format.charAt(i)) {
                result.append(text.charAt(i));
                txtIdx++;
            } else {
                result.append(format.charAt(i));
            }
        }

        if (exactly != true) {
            result.append(text.substring(txtIdx));
        }

        return result.toString();
    }

    /**
     * String 포멧 제거.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) StringUtil.unformat("2014/01/01", "####/##/##"); => 20140101
     * </pre>
     *
     * @param text   변환할 문자
     * @param format 바꿀 형식
     * @return
     */
    public static String unformat(String text, String format) {
        return unformat(text, format, '#', false);
    }

    /**
     * String 포멧 제거.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) StringUtil.unformat("2014/01/01", "@@@@/@@/@@", "@"); => 20140101
     * </pre>
     *
     * @param text   변환할 문자
     * @param format 바꿀 형식
     * @param symbol 형식으로 만든 기호 (default:#)
     * @return
     */
    public static String unformat(String text, String format, String symbol) {
        return unformat(text, format, symbol.charAt(0), false);
    }

    /**
     * String 포멧 제거.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) StringUtil.unformat("2014/01/01", "@@@@/@@/@@", "@"); => 20140101
     * </pre>
     *
     * @param text   변환할 문자
     * @param format 바꿀 형식
     * @param symbol 형식으로 만든 기호 (default:#)
     * @return
     */
    public static String unformat(String text, String format, char symbol) {
        return unformat(text, format, symbol, false);
    }

    /**
     * String 포멧 제거.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) StringUtil.unformat("2014/01/01", "@@@@/@@/@@", "@"); => 20140101
     * </pre>
     *
     * @param text    변환할 문자
     * @param format  바꿀 형식
     * @param exactly 형식 외에 문자 제거 유무 (default:false)
     * @return
     */
    public static String unformat(String text, String format, boolean exactly) {
        return unformat(text, format, '#', exactly);
    }

    /**
     * String 포멧 제거.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) StringUtil.unformat("2014/01/01", "@@@@/@@/@@", "@", false); => 20140101
     * </pre>
     *
     * @param text    변환할 문자
     * @param format  바꿀 형식
     * @param symbol  형식으로 만든 기호 (default:#)
     * @param exactly 형식 외에 문자 제거 유무 (default:false)
     * @return
     */
    public static String unformat(String text, String format, String symbol, boolean exactly) {
        return unformat(text, format, symbol.charAt(0), exactly);
    }

    /**
     * String 포멧 제거.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) StringUtil.unformat("2014/01/01", "@@@@/@@/@@", "@", false); => 20140101
     * </pre>
     *
     * @param text    변환할 문자
     * @param format  바꿀 형식
     * @param symbol  형식으로 만든 기호 (default:#)
     * @param exactly 형식 외에 문자 제거 유무 (default:false)
     * @return
     */
    public static String unformat(String text, String format, char symbol, boolean exactly) {
        if (StringUtils.isEmpty(text)) {
            return text;
        }
        if (StringUtils.isEmpty(format)) {
            return text;
        }

        StringBuilder result = new StringBuilder();
        int txtIdx = 0;

        for (int i = 0, ii = format.length(); i < ii; i++) {
            if (text.length() <= txtIdx) {
                break;
            }
            if (format.charAt(i) == symbol) {
                result.append(text.charAt(txtIdx++));
            } else if (text.length() > i && text.charAt(i) == format.charAt(i)) {
                txtIdx++;
            }
        }

        if (exactly != true) {
            result.append(text.substring(txtIdx));
        }

        return result.toString();
    }

    /**
     * 진수 변환 문자 출력.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) StringUtil.convertBase(20150731185733L, "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"); => 755492KP1
     * </pre>
     *
     * @param source 출력할 값
     * @param base   대상 문자
     * @return
     */
    public static String convertBase(long source, String base) {
        int baseLen = base.length();

        StringBuilder buf = new StringBuilder();

        while (source >= baseLen) {
            buf.insert(0, base.charAt((int) (source % baseLen)));
            source = source / baseLen;
        }

        buf.insert(0, base.charAt((int) source));

        return buf.toString();
    }

    /**
     * 배열 연결.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) StringUtil.join(array);
     * </pre>
     *
     * @param array 배열 객체
     * @return
     */
    public static String join(Object[] array) {
        return join(array, null);
    }

    /**
     * 구분자로 배열 연결.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) StringUtil.join(array, ",");
     * </pre>
     *
     * @param array     배열 객체
     * @param separator 연결 구분자 (default:"")
     * @return
     */
    public static String join(Object[] array, String separator) {
        if (array == null) {
            return null;
        }

        int bufSize = array.length;

        if (bufSize <= 0) {
            return "";
        }

        bufSize *= ((array[0] == null ? 16 : array[0].toString().length())
                + (separator == null ? 0 : separator.length()));

        StringBuilder buf = new StringBuilder(bufSize);

        for (int i = 0; i < array.length; i++) {
            if (separator != null && i > 0) {
                buf.append(separator);
            }
            if (array[i] != null) {
                buf.append(array[i]);
            }
        }

        return buf.toString();
    }

    /**
     * Iteator로 배열 연결.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) StringUtil.join(iterator);
     * </pre>
     *
     * @param iterator iterator 객체
     * @return
     */
    public static String join(Iterator<Object> iterator) {
        return join(iterator, null);
    }

    /**
     * Iteator 구분자로 배열 연결.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) StringUtil.join(iterator, ",");
     * </pre>
     *
     * @param iterator  iterator 객체
     * @param separator 연결 구분자 (default:"")
     * @return
     */
    public static String join(Iterator<Object> iterator, String separator) {
        if (iterator == null) {
            return null;
        }

        if (!iterator.hasNext()) {
            return "";
        }

        Object first = iterator.next();

        if (!iterator.hasNext()) {
            return (String) first;
        }

        StringBuilder buf = new StringBuilder(256);

        if (first != null) {
            buf.append(first);
        }

        while (iterator.hasNext()) {
            if (separator != null) {
                buf.append(separator);
            }
            Object obj = iterator.next();
            if (obj != null) {
                buf.append(obj);
            }
        }

        return buf.toString();
    }

    /**
     * 문자 찾기.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) StringUtil.matches("text", "a");
     * </pre>
     *
     * @param regex 대상 문자
     * @param input 찾을 문자
     * @return
     */
    public static boolean matches(String regex, CharSequence input) {
        return "*".equals(regex) || Pattern.matches(regex.replaceAll("\\*", "([^\\\\t\\\\n\\\\r\\\\f\\\\v]*)"), input);
    }

    /**
     * 사이즈(용량) 계산(KB, MB, GB, TB).
     *
     * @param size
     * @return
     */
    public static long parseSize(String size) {
        if (isEmpty(size)) {
            return 0;
        }

        size = size.toUpperCase();

        if (size.endsWith("KB")) {
            return Long.valueOf(size.replace("KB", ""), 10) * 1024;
        }

        if (size.endsWith("MB")) {
            return Long.valueOf(size.replace("MB", ""), 10) * 1024 * 1024;
        }

        if (size.endsWith("GB")) {
            return Long.valueOf(size.replace("GB", ""), 10) * 1024 * 1024 * 1024;
        }

        if (size.endsWith("TB")) {
            return Long.valueOf(size.replace("TB", ""), 10) * 1024 * 1024 * 1024 * 1024;
        }

        return Long.valueOf(size, 10);
    }

    /**
     * 두 문자열을 비교하여 일치하는 경우 {@code true} 반환.<br>
     * 단, 비교대상 문자열이 null인 경우 공백("")으로 치환하여 비교
     *
     * <pre>
     * StringUtil.equalsIfNullToEmpty(null, null)   = true
     * StringUtil.equalsIfNullToEmpty(null, "")     = true
     * StringUtil.equalsIfNullToEmpty("", null)     = true
     * StringUtil.equalsIfNullToEmpty(null, "abc")  = false
     * StringUtil.equalsIfNullToEmpty("abc", null)  = false
     * StringUtil.equalsIfNullToEmpty("abc", "abc") = true
     * StringUtil.equalsIfNullToEmpty("abc", "ABC") = false
     * </pre>
     *
     * @param str1
     * @param str2
     * @return
     */
    public static boolean equalsIfNullToEmpty(final CharSequence str1, final CharSequence str2) {
        return StringUtils.defaultIfEmpty(str1, "").equals(StringUtils.defaultIfEmpty(str2, ""));
    }

    /**
     * 두 문자열을 비교하여 일치하는 경우 {@code true} 반환.<br>
     * 단, 비교대상 문자열이 null인 경우 공백("")으로 치환하여 비교하며, 대소문자를 구별하지 않음
     *
     * <pre>
     * StringUtil.equalsIfNullToEmptyIgnoreCase(null, null)   = true
     * StringUtil.equalsIfNullToEmptyIgnoreCase(null, "")     = true
     * StringUtil.equalsIfNullToEmptyIgnoreCase("", null)     = true
     * StringUtil.equalsIfNullToEmptyIgnoreCase(null, "abc")  = false
     * StringUtil.equalsIfNullToEmptyIgnoreCase("abc", null)  = false
     * StringUtil.equalsIfNullToEmptyIgnoreCase("abc", "abc") = true
     * StringUtil.equalsIfNullToEmptyIgnoreCase("abc", "ABC") = true
     * </pre>
     *
     * @param str1
     * @param str2
     * @return
     */
    public static boolean equalsIfNullToEmptyIgnoreCase(final CharSequence str1, final CharSequence str2) {
        return StringUtils.defaultIfEmpty(str1, "").equals(StringUtils.defaultIfEmpty(str2, ""));
    }
}
