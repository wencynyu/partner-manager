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
import top.wenxyn.partner.manager.entity.dao.auth.TAuthMenu;
import top.wenxyn.partner.manager.entity.vo.PageVO;
import top.wenxyn.partner.manager.service.auth.MenuService;
import top.wenxyn.partner.manager.util.ResponseUtil;

import java.util.List;

/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/3/6 23:10
 */
@RestController
@RequestMapping("menu")
@Slf4j
@Api(value = "提供动态菜单相关的 Rest API", tags = {"动态菜单相关接口"})
public class MenuController {

    @Autowired
    private MenuService menuService;

    @ApiOperation("分页获取全部菜单信息")
    @PreAuthorize("hasAnyAuthority('PERMISSION_getAllMenuByPageVO') || hasAnyRole('ROLE_admin')")
    @PostMapping("getAllMenuByPageVO")
    public ResponseEntity getAllMenuByPageVO(@RequestBody PageVO pageVO){
        try {
            Page<TAuthMenu> tAuthMenus = menuService.queryAllByPage(pageVO);
            return ResponseEntity.ok(tAuthMenus);
        }catch (Exception e){
            log.error("getAllMenuByPageVO fail, error message:{}", e.getMessage());
        }
        return ResponseUtil.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ApiOperation("获取用户的全部菜单信息")
    @PreAuthorize("hasAnyAuthority('PERMISSION_getAllMenu') || hasAnyRole('ROLE_admin')")
    @PostMapping("getAllMenu")
    public ResponseEntity getAllMenu(){
        try {
            List<TAuthMenu> tAuthMenus = menuService.queryAllByUser();
            return ResponseEntity.ok(tAuthMenus);
        }catch (Exception e){
            log.error("getAllMenuByPageVO fail, error message:{}", e.getMessage());
        }
        return ResponseUtil.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ApiOperation("添加新菜单")
    @PreAuthorize("hasAnyAuthority('PERMISSION_addMenu') || hasAnyRole('ROLE_admin')")
    @PostMapping("addMenu")
    public ResponseEntity addMenu(@RequestBody TAuthMenu menu){

        try {
            TAuthMenu insert = menuService.insert(menu);
            return ResponseEntity.ok(insert);
        }catch (Exception e){
            log.error("menu insert fail, error message:{}", e.getMessage());
        }
        return ResponseUtil.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ApiOperation("修改菜单信息")
    @PreAuthorize("hasAnyAuthority('PERMISSION_updateMenu') || hasAnyRole('ROLE_admin')")
    @PostMapping("updateMenu")
    public ResponseEntity updateMenu(@RequestBody TAuthMenu menu){

        try {
            TAuthMenu update = menuService.update(menu);
            return ResponseEntity.ok(update);
        }catch (Exception e){
            log.error("menu update fail, error message:{}", e.getMessage());
        }
        return ResponseUtil.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ApiOperation("删除操作")
    @PreAuthorize("hasAnyAuthority('PERMISSION_delete') || hasAnyRole('ROLE_admin')")
    @DeleteMapping("delete")
    public ResponseEntity delete(@RequestParam Integer id){
        try {
            menuService.deleteById(id);
            return ResponseEntity.ok("delete success");
        }catch (Exception e){
            log.error("delete error : {}", e.getMessage());
        }
        return ResponseUtil.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
