package top.wenxyn.partner.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.wenxyn.partner.manager.dao.TUserRepository;
import top.wenxyn.partner.manager.dao.TUserRoleRepository;
import top.wenxyn.partner.manager.entity.TAuthUser;
import top.wenxyn.partner.manager.entity.TAuthUserRole;
import top.wenxyn.partner.manager.exception.RegisterException;
import top.wenxyn.partner.manager.util.EncryptUtil;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/2/27 19:48
 */
@Service
@Transactional
public class UserService extends AbstractService<TAuthUser, Integer, TUserRepository> {

    @Autowired
    private TUserRoleRepository tUserRoleRepository;

    @Transactional
    public void register(TAuthUser regUser){
        TAuthUser byUsername = repository.findByUsername(regUser.getUsername());
        if (byUsername != null){
            throw new RegisterException("register fail, username has been used.");
        }
        String originPassword = regUser.getPassword();
        String encryptedPassword = EncryptUtil.encode(originPassword);
        regUser.setPassword(encryptedPassword);
        TAuthUser user = repository.save(regUser);
        TAuthUserRole visitorRole = new TAuthUserRole();
        visitorRole.setRoleId(RegisterRole.VISITOR.getVal());
        visitorRole.setUserId(user.getId());
        tUserRoleRepository.save(visitorRole);
        TAuthUserRole userRole = new TAuthUserRole();
        userRole.setRoleId(RegisterRole.USER.getVal());
        userRole.setUserId(user.getId());
        tUserRoleRepository.save(userRole);
    }

    public void insertUserRoleRelation(List<TAuthUserRole> tAuthRoleMenuList) {
        tUserRoleRepository.saveAll(tAuthRoleMenuList);
    }

    enum RegisterRole{
        VISITOR(2),
        USER(3);
        private Integer val;
        RegisterRole(Integer val){
            this.val = val;
        }

        public Integer getVal() {
            return val;
        }
    }
}
