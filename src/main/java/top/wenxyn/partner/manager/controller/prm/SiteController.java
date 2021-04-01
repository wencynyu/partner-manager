package top.wenxyn.partner.manager.controller.prm;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import top.wenxyn.partner.manager.entity.dao.prm.TPrmPartner;
import top.wenxyn.partner.manager.entity.dao.prm.TPrmSite;
import top.wenxyn.partner.manager.entity.vo.PageVO;
import top.wenxyn.partner.manager.service.prm.SiteService;
import top.wenxyn.partner.manager.util.ResponseUtil;

/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/3/18 0:41
 */
@RestController
@RequestMapping("site")
@Slf4j
@Api(value = "提供合作方站点相关的 Rest API", tags = {"合作方站点相关接口"})
public class SiteController {

    @Autowired
    private SiteService siteService;

    @ApiOperation("分页获取全部合作方站点信息")
    @PreAuthorize("hasAnyAuthority('PERMISSION_getAllSiteByPageVO') || hasAnyRole('ROLE_admin')")
    @PostMapping("getAllSiteByPageVO")
    public ResponseEntity getAllSiteByPageVO(@RequestBody PageVO pageVO){
        try {
            Page<TPrmSite> tPrmSites = siteService.queryAllByPage(pageVO);
            return ResponseEntity.ok(tPrmSites);
        }catch (Exception e){
            log.error("getAllSiteByPageVO fail, error message:{}", e.getMessage());
        }
        return ResponseUtil.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ApiOperation("更新站点信息")
    @PreAuthorize("hasAnyAuthority('PERMISSION_updateSite') || hasAnyRole('ROLE_admin')")
    @PostMapping("updateSite")
    public ResponseEntity updateSite(@RequestBody TPrmSite tPrmSite){
        try {
            TPrmSite update = siteService.update(tPrmSite);
            return ResponseEntity.ok(update);
        }catch (Exception e){
            log.error("getAllPartnerByPageVO fail, error message:{}", e.getMessage());
        }
        return ResponseUtil.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ApiOperation("新增站点信息")
    @PreAuthorize("hasAnyAuthority('PERMISSION_createSite') || hasAnyRole('ROLE_admin')")
    @PostMapping("createSite")
    public ResponseEntity createSite(@RequestBody TPrmSite tPrmSite){
        try {
            TPrmSite insert = siteService.insert(tPrmSite);
            return ResponseEntity.ok(insert);
        }catch (Exception e){
            log.error("getAllPartnerByPageVO fail, error message:{}", e.getMessage());
        }
        return ResponseUtil.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ApiOperation("删除操作")
    @PreAuthorize("hasAnyAuthority('PERMISSION_delete') || hasAnyRole('ROLE_admin')")
    @DeleteMapping("delete")
    public ResponseEntity delete(@RequestParam Integer id){
        try {
            siteService.deleteById(id);
            return ResponseEntity.ok("delete success");
        }catch (Exception e){
            log.error("delete error : {}", e.getMessage());
        }
        return ResponseUtil.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
