package top.wenxyn.partner.manager.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.session.Session;
import org.springframework.stereotype.Service;
import top.wenxyn.partner.manager.repository.auth.TUserRepository;
import top.wenxyn.partner.manager.repository.auth.TUserRoleRepository;
import top.wenxyn.partner.manager.entity.dao.auth.TAuthUser;
import top.wenxyn.partner.manager.entity.dao.auth.TAuthUserRole;
import top.wenxyn.partner.manager.exception.RegisterException;
import top.wenxyn.partner.manager.service.AbstractService;
import top.wenxyn.partner.manager.util.AuthUtil;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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
        String encryptedPassword = AuthUtil.encode(originPassword);
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

    public void changePassword(String oldPassword, String newPassword) {
        SystemUser curUser = AuthUtil.getCurUser();
        boolean match = AuthUtil.match(oldPassword, curUser.getPassword());
        if (!match){
            throw new RuntimeException("old password error.");
        }
        Optional<TAuthUser> user = repository.findById(curUser.getId());
        TAuthUser tAuthUser = user.orElseThrow(() ->
                new RuntimeException("there is no user named [" + curUser.getUsername() + "]"));
        tAuthUser.setPassword(newPassword);
        repository.save(tAuthUser);
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
