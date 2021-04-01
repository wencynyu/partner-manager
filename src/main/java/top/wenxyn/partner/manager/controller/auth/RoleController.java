package top.wenxyn.partner.manager.controller.auth;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import top.wenxyn.partner.manager.entity.dao.auth.TAuthRole;
import top.wenxyn.partner.manager.entity.dao.auth.TAuthRoleMenu;
import top.wenxyn.partner.manager.entity.dao.auth.TAuthRolePermission;
import top.wenxyn.partner.manager.entity.vo.PageVO;
import top.wenxyn.partner.manager.service.auth.RoleService;
import top.wenxyn.partner.manager.util.ResponseUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/3/4 0:33
 */

@RestController
@RequestMapping("role")
@Slf4j
@Api(value = "提供角色相关的 Rest API", tags = {"角色相关接口"})
public class RoleController {
    @Autowired
    private RoleService roleService;

    @ApiOperation("分页获取全部角色信息")
    @PreAuthorize("hasAnyAuthority('PERMISSION_getAllRoleByPageVO') || hasAnyRole('ROLE_admin')")
    @PostMapping("getAllRoleByPageVO")
    public ResponseEntity getAllRoleByPageVO(@RequestBody PageVO pageVO){
        try {
            Page<TAuthRole> tAuthRoles = roleService.queryAllByPage(pageVO);
            return ResponseEntity.ok(tAuthRoles);
        }catch (Exception e){
            log.error("getAllRoleByPageVO fail, error message:{}", e.getMessage());
        }
        return ResponseUtil.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ApiOperation("添加新角色")
    @PreAuthorize("hasAnyAuthority('PERMISSION_addRole') || hasAnyRole('ROLE_admin')")
    @PostMapping("addRole")
    public ResponseEntity addRole(@RequestBody TAuthRole role){

        try {
            TAuthRole insert = roleService.insert(role);
            return ResponseEntity.ok(insert);
        }catch (Exception e){
            log.error("user insert fail, error message:{}", e.getMessage());
        }
        return ResponseUtil.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ApiOperation("添加角色与权限的绑定信息")
    @PreAuthorize("hasAnyAuthority('PERMISSION_addRolePermissionRelation') || hasAnyRole('ROLE_admin')")
    @PostMapping("addRolePermissionRelation")
    public ResponseEntity addRolePermissionRelation(@RequestBody Set<Integer> roleIds,
                                                    @RequestBody Set<Integer> permissionIds){
        List<TAuthRolePermission> tAuthRolePermissionList = new ArrayList<>();
        for (Integer roleId :
                roleIds) {
            for (Integer permissionId :
                    permissionIds) {
                tAuthRolePermissionList.add(new TAuthRolePermission(roleId, permissionId));
            }
        }

        try {
            roleService.insertRolePermissionRelation(tAuthRolePermissionList);
            return ResponseEntity.ok("insert role permission relation success.");
        }catch (Exception e){
            log.error("addRolePermissionRelation fail, error message: {}", e.getMessage());
        }
        return ResponseUtil.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ApiOperation("添加角色与菜单的绑定信息")
    @PreAuthorize("hasAnyAuthority('PERMISSION_addRoleMenuRelation') || hasAnyRole('ROLE_admin')")
    @PostMapping("addRoleMenuRelation")
    public ResponseEntity addRoleMenuRelation(@RequestBody Set<Integer> roleIds,
                                              @RequestBody Set<Integer> menuIds){
        List<TAuthRoleMenu> tAuthRoleMenuList = new ArrayList<>();
        for (Integer roleId :
                roleIds) {
            for (Integer menuId :
                    menuIds) {
                tAuthRoleMenuList.add(new TAuthRoleMenu(roleId, menuId));
            }
        }

        try {
            roleService.insertRoleMenuRelation(tAuthRoleMenuList);
            return ResponseEntity.ok("insert role menu relation success.");
        }catch (Exception e){
            log.error("addRoleMenuRelation fail, error message: {}", e.getMessage());
        }
        return ResponseUtil.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ApiOperation("删除操作")
    @PreAuthorize("hasAnyAuthority('PERMISSION_delete') || hasAnyRole('ROLE_admin')")
    @DeleteMapping("delete")
    public ResponseEntity delete(@RequestParam Integer id){
        try {
            roleService.deleteById(id);
            return ResponseEntity.ok("delete success");
        }catch (Exception e){
            log.error("delete error : {}", e.getMessage());
        }
        return ResponseUtil.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
