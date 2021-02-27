package top.wenxyn.partner.manager.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.wenxyn.partner.manager.dao.*;
import top.wenxyn.partner.manager.entity.*;
import top.wenxyn.partner.manager.util.QueryUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/2/27 19:48
 */
@Service
public class SystemUserDetailsService implements UserDetailsService {

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

    @Override
    public UserDetails loadUserByUsername(String s)  {
        TAuthUser authUser = tUserRepository.findByUsername(s);
        if (authUser == null){
            throw new UsernameNotFoundException("user:[" + s +"] doesn't exist.");
        }
        Set<Integer> roleIds = tUserRoleRepository.findByUserId(authUser.getId())
                .stream()
                .map(TAuthUserRole::getRoleId)
                .collect(Collectors.toSet());

        List<TAuthRole> roles = tRoleRepository.findAllById(roleIds);

        roleIds = roles.stream()
                .map(TAuthRole::getId)
                .collect(Collectors.toSet());

        Specification<TAuthRolePermission> specification =
                QueryUtil.buildBatchQuerySpecification("role_id", roleIds);

        Set<Integer> permissionIds = tRolePermissionRepository.findAll(specification)
                .stream()
                .map(TAuthRolePermission::getPermissionId)
                .collect(Collectors.toSet());
        List<TAuthPermission> permissions = tPermissionRepository.findAllById(permissionIds);

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        roles.forEach(tAuthRole -> grantedAuthorities.add(new SimpleGrantedAuthority(tAuthRole.getName())));
        permissions.forEach(tAuthPermission -> grantedAuthorities.add(new SimpleGrantedAuthority(tAuthPermission.getName())));
        return new User(authUser.getUsername(), authUser.getPassword(), grantedAuthorities);
    }
}