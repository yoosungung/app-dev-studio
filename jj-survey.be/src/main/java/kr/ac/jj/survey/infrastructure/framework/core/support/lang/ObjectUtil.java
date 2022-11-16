package kr.ac.jj.survey.infrastructure.framework.core.support.lang;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Base64Utils;

import kr.ac.jj.survey.infrastructure.framework.core.foundation.exception.BaseException;
import kr.ac.jj.survey.infrastructure.framework.core.support.io.util.IOUtil;

public class ObjectUtil {
    /**
     * 객체를 직렬화하여 문자열로 반환.
     *
     * @param object 직렬화할 객체
     * @return 직렬화된 Base64 인코딩 문자열
     */
    public static String serializeToString(Object object) {
        if (object == null) {
            return null;
        }

        ByteArrayOutputStream baos = null;
        ObjectOutputStream oos = null;

        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            return new String(Base64Utils.encode(baos.toByteArray()));
        } catch (IOException e) {
            throw new BaseException(e);
        } finally {
            IOUtil.closeQuietly(baos, oos);
        }
    }

    /**
     * 문자열을 역직렬화하여 객체로 반환.
     *
     * @param string 역직렬화할 Base64 인코딩 문자열
     * @return 역직렬화된 객체
     */
    @SuppressWarnings("unchecked")
    public static <T> T deserializeToObject(String string) {
        if (StringUtils.isEmpty(string)) {
            return null;
        }

        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;

        try {
            byte[] data = Base64Utils.decodeFromString(string);
            bais = new ByteArrayInputStream(data);
            ois = new ObjectInputStream(bais);
            return (T) ois.readObject();
        } catch (IOException e) {
            throw new BaseException(e);
        } catch (ClassNotFoundException e) {
            throw new BaseException(e);
        } finally {
            IOUtil.closeQuietly(bais, ois);
        }
    }
}
