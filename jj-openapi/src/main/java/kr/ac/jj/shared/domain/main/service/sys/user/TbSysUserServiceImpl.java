package kr.ac.jj.shared.domain.main.service.sys.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.jj.shared.domain.main.mapper.com.person.user.TbComPersonUserMapper;
import kr.ac.jj.shared.domain.main.mapper.sys.user.TbSysUserMapper;
import kr.ac.jj.shared.domain.main.mapper.sys.user.author.TbSysUserAuthorMapper;
import kr.ac.jj.shared.domain.main.mapper.sys.user.setup.TbSysUserSetupMapper;

/**
 * 시스템 - 사용자 Service
 */
@Service
public class TbSysUserServiceImpl {

    private @Autowired TbSysUserAuthorMapper tbSysUserAuthorMapper;
    private @Autowired TbSysUserSetupMapper tbSysUserSetupMapper;
    private @Autowired TbComPersonUserMapper tbComPersonUserMapper;
    private @Autowired TbSysUserMapper tbSysUserMapper;

    /**
     * 삭제
     *
     * @param userId
     */
    public void delete(String userId) {
        tbSysUserAuthorMapper.deleteListByUserId(userId);
        tbSysUserSetupMapper.deleteListByUserId(userId);
        tbComPersonUserMapper.deleteByUserId(userId);
        tbSysUserMapper.delete(userId);
    }

}
