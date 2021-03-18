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
import top.wenxyn.partner.manager.entity.dao.auth.TAuthPermission;
import top.wenxyn.partner.manager.entity.vo.PageVO;
import top.wenxyn.partner.manager.service.auth.PermissionService;
import top.wenxyn.partner.manager.util.ResponseUtil;

/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/3/4 0:33
 */
@RestController
@RequestMapping("permission")
@Slf4j
@Api(value = "提供权限相关的 Rest API", tags = {"权限相关接口"})
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @ApiOperation("分页获取全部权限信息")
    @PreAuthorize("hasAnyAuthority('PERMISSION_getAllPermissionByPageVO')")
    @PostMapping("getAllPermissionByPageVO")
    public ResponseEntity getAllPermissionByPageVO(@RequestBody PageVO pageVO){
        try {
            Page<TAuthPermission> tAuthPermissions = permissionService.queryAllByPage(pageVO);
            return ResponseEntity.ok(tAuthPermissions);
        }catch (Exception e){
            log.error("getAllPermissionByPageVO fail, error message:{}", e.getMessage());
        }
        return ResponseUtil.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ApiOperation("添加新权限")
    @PreAuthorize("hasAnyAuthority('PERMISSION_addPermission')")
    @PostMapping("addPermission")
    public ResponseEntity addPermission(@RequestBody TAuthPermission permission){

        try {
            TAuthPermission insert = permissionService.addPermission(permission);
            return ResponseEntity.ok(insert);
        }catch (Exception e){
            log.error("permission insert fail, error message:{}", e.getMessage());
        }
        return ResponseUtil.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ApiOperation("删除操作")
    @PreAuthorize("hasAnyAuthority('PERMISSION_delete')")
    @DeleteMapping("delete")
    public ResponseEntity delete(@RequestParam Integer id){
        try {
            permissionService.deleteById(id);
            return ResponseEntity.ok("delete success");
        }catch (Exception e){
            log.error("delete error : {}", e.getMessage());
        }
        return ResponseUtil.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
