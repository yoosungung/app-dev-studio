package kr.ac.jj.shared.domain.main.service.com.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.jj.shared.domain.main.mapper.com.person.TbComPersonMapper;
import kr.ac.jj.shared.domain.main.mapper.com.person.dept.TbComPersonDeptMapper;
import kr.ac.jj.shared.domain.main.mapper.com.person.user.TbComPersonUserMapper;

/**
 * 공통 - 사람 Service
 */
@Service
public class TbComPersonServiceImpl {

    private @Autowired TbComPersonDeptMapper tbComPersonDeptMapper;
    private @Autowired TbComPersonUserMapper tbComPersonUserMapper;
    private @Autowired TbComPersonMapper tbComPersonMapper;

    /**
     * 삭제
     *
     * @param personId
     */
    public void delete(String personId) {
        tbComPersonDeptMapper.deleteListByPersonId(personId);
        tbComPersonUserMapper.deleteByPersonId(personId);
        tbComPersonMapper.delete(personId);
    }

}
