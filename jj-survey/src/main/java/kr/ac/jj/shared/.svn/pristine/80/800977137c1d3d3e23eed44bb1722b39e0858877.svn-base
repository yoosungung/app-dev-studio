package kr.ac.jj.shared.domain.main.service.sys.intrfc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.jj.shared.domain.main.mapper.sys.intrfc.TbSysIntrfcMapper;
import kr.ac.jj.shared.domain.main.model.sys.intrfc.TbSysIntrfc;

/**
 * 시스템 - 인터페이스 Service
 */
@Service
public class TbSysIntrfcServiceImpl {

    private @Autowired TbSysIntrfcMapper tbSysIntrfcMapper;

    /**
     * 생성
     *
     * @param tableNm
     * @param idValue
     * @return
     */
    public int create(String tableNm, String idValue) {
        TbSysIntrfc tbSysIntrfc = new TbSysIntrfc();
        tbSysIntrfc.setTableNm(tableNm);
        tbSysIntrfc.setIdValue(idValue);

        return tbSysIntrfcMapper.insert(tbSysIntrfc);
    }

    /**
     * 전체 삭제
     *
     * @param tableNm
     * @return
     */
    public int deleteAll(String tableNm) {
        return tbSysIntrfcMapper.deleteAll(tableNm);
    }

}
