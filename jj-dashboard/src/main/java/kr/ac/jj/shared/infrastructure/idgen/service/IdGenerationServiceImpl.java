package kr.ac.jj.shared.infrastructure.idgen.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.jj.shared.domain.main.mapper.sys.uid.TbSysUidMapper;
import kr.ac.jj.shared.domain.main.model.sys.uid.TbSysUid;
import kr.ac.jj.shared.infrastructure.idgen.mapper.IdGenerationMapper;

/**
 * ID 생성 Service
 */
@Service
public class IdGenerationServiceImpl {

    private static final Logger log = LoggerFactory.getLogger(IdGenerationServiceImpl.class);

    private @Autowired IdGenerationMapper idGenerationMapper;
    private @Autowired TbSysUidMapper tbSysUidMapper;

    public String getUid() {
        return this.getUid('0');
    }

    public String getUid(char type) {
        return idGenerationMapper.selectUid(type);
    }

    public String createUid(char type) {
        return this.createUid(type, null, 0);
    }

    public String createUid(String uidSe) {
        return this.createUid('0', uidSe, 0);
    }

    public String createUid(char type, String uidSe) {
        return this.createUid(type, uidSe, 0);
    }

    private String createUid(char type, String uidSe, int retryCnt) {
        String uidValue = idGenerationMapper.selectUid(type);

        log.info("type: {}, uidSe: {}, retryCnt: {} => uidValue: {}", type, uidSe, retryCnt, uidValue);

        try {
            TbSysUid tbSysUid = new TbSysUid();
            tbSysUid.setUidValue(uidValue);
            tbSysUid.setUidSe(StringUtils.defaultIfEmpty(uidSe, "UNKNOWN"));
            tbSysUidMapper.insert(tbSysUid);
        } catch (RuntimeException e) {
            if (retryCnt < 2) {
                return this.createUid(type, uidSe, retryCnt + 1);
            }
            throw e;
        }

        return uidValue;
    }

}
