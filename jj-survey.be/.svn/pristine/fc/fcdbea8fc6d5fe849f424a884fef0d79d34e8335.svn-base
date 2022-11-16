package kr.ac.jj.survey.infrastructure.idgen.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.ac.jj.survey.infrastructure.idgen.service.IdGenerationServiceImpl;

/**
 * ID 생성 Util
 */
@Component
public class IdGenerationUtil {
    private static IdGenerationServiceImpl idGenerationService;

    private IdGenerationUtil(@Autowired IdGenerationServiceImpl idGenerationService) {
        IdGenerationUtil.idGenerationService = idGenerationService;
    }

    public static String getUid() {
        return idGenerationService.getUid('0');
    }

    public static String getUid(char type) {
        return idGenerationService.getUid(type);
    }

    public static String createUid() {
        return idGenerationService.createUid(null);
    }

    public static String createUid(char type) {
        return idGenerationService.createUid(type);
    }

    public static String createUid(String uidSe) {
        return idGenerationService.createUid(uidSe);
    }

    public static String createUid(char type, String uidSe) {
        return idGenerationService.createUid(type, uidSe);
    }
}
