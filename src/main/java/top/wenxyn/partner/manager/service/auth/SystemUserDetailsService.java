package top.wenxyn.partner.manager.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.wenxyn.partner.manager.common.Constant;
import top.wenxyn.partner.manager.repository.auth.*;
import top.wenxyn.partner.manager.entity.dao.auth.*;
import top.wenxyn.partner.manager.util.QueryUtil;
import top.wenxyn.partner.manager.util.SerializeUtil;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/2/27 19:48
 */
@Service
public class SystemUserDetailsService implements UserDetailsService {
    private static final String ROLE_PREFIX = "ROLE_";
    private static final String PERMISSION_PREFIX = "PERMISSION_";

    @Autowired
    private TUserRepository tUserRepository;

    @Autowired
    private TUserRoleRepository tUserRoleRepository;

    @Autowired
    private TRoleRepository tRoleRepository;

    @Autowired
    private TRolePermissionRepository tRolePermissionRepository;

    @Autowired
    private TPermissionRepository tPermissionRepository;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final String USER_DETAILS_SUFFIX = Constant.SEPARATE_COMMA + Constant.USER_DETAILS_SUFFIX;
    @Override
    public UserDetails loadUserByUsername(String s)  {
        UserDetails redisUser = SerializeUtil.string2Obj(stringRedisTemplate.opsForValue().get(s + USER_DETAILS_SUFFIX), SystemUser.class);
        if (redisUser != null){
            return redisUser;
        }

        TAuthUser authUser = Optional.ofNullable(SerializeUtil.string2Obj(stringRedisTemplate.opsForValue().get(s), TAuthUser.class))
                .orElse(tUserRepository.findByUsername(s));
        if (authUser == null){
            throw new UsernameNotFoundException("user:[" + s +"] doesn't exist.");
        }
        Set<Integer> roleIds = tUserRoleRepository.findByUserId(authUser.getId())
                .stream()
                .map(TAuthUserRole::getRoleId)
                .collect(Collectors.toSet());

        List<TAuthRole> roles = tRoleRepository.findAllById(roleIds);

        Specification<TAuthRolePermission> specification =
                QueryUtil.buildBatchQuerySpecification("roleId", roleIds);

        Set<Integer> permissionIds = tRolePermissionRepository.findAll(specification)
                .stream()
                .map(TAuthRolePermission::getPermissionId)
                .collect(Collectors.toSet());
        List<TAuthPermission> permissions = tPermissionRepository.findAllById(permissionIds);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        roles.forEach(tAuthRole -> grantedAuthorities.add(new SimpleGrantedAuthority(convertToRole(tAuthRole.getName()))));
        permissions.forEach(tAuthPermission -> grantedAuthorities.add(new SimpleGrantedAuthority(convertToPermission(tAuthPermission.getName()))));
        SystemUser user = SystemUser.builder().username(authUser.getUsername())
                .id(authUser.getId())
                .password(authUser.getPassword())
                .enabled(authUser.getStatus() == 0)
                .accountNonLocked(authUser.getLoginTryTimes() <= 5)
                .authorities(grantedAuthorities)
                .accountNonExpired(true)
                .credentialsNonExpired(true)
                .build();
        authUser.setLoginTryTimes(authUser.getLoginTryTimes() + 1);
        tUserRepository.save(authUser);
        stringRedisTemplate.opsForValue().set(s + USER_DETAILS_SUFFIX, SerializeUtil.obj2String(user), 30, TimeUnit.MINUTES);
        return user;
    }

    private String convertToRole(String roleName) {
        return ROLE_PREFIX + roleName;
    }

    private String convertToPermission(String permissionName){
        return PERMISSION_PREFIX + permissionName;
    }
}