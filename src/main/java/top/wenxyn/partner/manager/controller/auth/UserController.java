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
import top.wenxyn.partner.manager.entity.dao.auth.TAuthUser;
import top.wenxyn.partner.manager.entity.dao.auth.TAuthUserRole;
import top.wenxyn.partner.manager.entity.vo.PageVO;
import top.wenxyn.partner.manager.service.auth.UserService;
import top.wenxyn.partner.manager.util.ResponseUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/2/27 19:48
 */
@RestController
@RequestMapping("user")
@Slf4j
@Api(value = "提供用户相关的 Rest API", tags = {"用户相关接口"})
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("获取用户数量")
    @PreAuthorize("hasAnyAuthority('PERMISSION_getUserCount') || hasAnyRole('ROLE_admin')")
    @PostMapping("getUserCount")
    public ResponseEntity getUserCount(){
        try {
            long count = userService.queryCount();
            return ResponseEntity.ok(count);
        }catch (Exception e){
            log.error("getAllUserByPageVO fail, error message:{}", e.getMessage());
        }
        return ResponseUtil.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ApiOperation("分页获取全部用户信息")
    @PreAuthorize("hasAnyAuthority('PERMISSION_getAllUserByPageVO') || hasAnyRole('ROLE_admin')")
    @PostMapping("getAllUserByPageVO")
    public ResponseEntity getAllUserByPageVO(@RequestBody PageVO pageVO){
        try {
            Page<TAuthUser> tAuthUsers = userService.queryAllByPage(pageVO);
            return ResponseEntity.ok(tAuthUsers);
        }catch (Exception e){
            log.error("getAllUserByPageVO fail, error message:{}", e.getMessage());
        }
        return ResponseUtil.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ApiOperation("添加新用户/非注册行为")
    @PreAuthorize("hasAnyAuthority('PERMISSION_addUser') || hasAnyRole('ROLE_admin')")
    @PostMapping("addUser")
    public ResponseEntity addUser(@RequestBody TAuthUser user){

        try {
            TAuthUser insert = userService.insert(user);
            return ResponseEntity.ok(insert);
        }catch (Exception e){
            log.error("user insert fail, error message:{}", e.getMessage());
        }
        return ResponseUtil.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ApiOperation("添加用户与角色的绑定信息")
    @PreAuthorize("hasAnyAuthority('PERMISSION_addUserRoleRelation') || hasAnyRole('ROLE_admin')")
    @PostMapping("addUserRoleRelation")
    public ResponseEntity addRoleMenuRelation(@RequestBody Set<Integer> userIds,
                                              @RequestBody Set<Integer> roleIds){
        List<TAuthUserRole> tAuthUserRoleList = new ArrayList<>();
        for (Integer userId :
                userIds) {
            for (Integer roleId :
                    roleIds) {
                tAuthUserRoleList.add(new TAuthUserRole(userId, roleId));
            }
        }

        try {
            userService.insertUserRoleRelation(tAuthUserRoleList);
            return ResponseEntity.ok("insert user role relation success.");
        }catch (Exception e){
            log.error("addUserRoleRelation fail, error message: {}", e.getMessage());
        }
        return ResponseUtil.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ApiOperation("修改密码")
    @PreAuthorize("hasAnyAuthority('PERMISSION_changePassword') || hasAnyRole('ROLE_admin')")
    @PostMapping("changePassword")
    public ResponseEntity changePassword(@RequestParam String oldPassword,
                                         @RequestParam String newPassword){
        try {
            userService.changePassword(oldPassword, newPassword);
            return ResponseEntity.ok("change password success");
        }catch (Exception e){
            log.error("delete error : {}", e.getMessage());
        }
        return ResponseUtil.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ApiOperation("修改用户信息")
    @PreAuthorize("hasAnyAuthority('PERMISSION_updateUser') || hasAnyRole('ROLE_admin')")
    @PostMapping("updateUser")
    public ResponseEntity updateUser(@RequestBody TAuthUser user){

        try {
            TAuthUser update = userService.update(user);
            return ResponseEntity.ok(update);
        }catch (Exception e){
            log.error("user update fail, error message:{}", e.getMessage());
        }
        return ResponseUtil.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ApiOperation("删除操作")
    @PreAuthorize("hasAnyAuthority('PERMISSION_delete') || hasAnyRole('ROLE_admin')")
    @DeleteMapping("delete")
    public ResponseEntity delete(@RequestParam Integer id){
        try {
            userService.deleteById(id);
            return ResponseEntity.ok("delete success");
        }catch (Exception e){
            log.error("delete error : {}", e.getMessage());
        }
        return ResponseUtil.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
