package top.wenxyn.partner.manager.controller.prm;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import top.wenxyn.partner.manager.entity.dao.prm.TPrmPartner;
import top.wenxyn.partner.manager.entity.vo.PageVO;
import top.wenxyn.partner.manager.service.auth.SystemUser;
import top.wenxyn.partner.manager.service.prm.BankService;
import top.wenxyn.partner.manager.service.prm.PartnerService;
import top.wenxyn.partner.manager.service.prm.PaymentAddressService;
import top.wenxyn.partner.manager.util.ResponseUtil;

/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/3/13 19:59
 */
@RestController
@RequestMapping("partner")
@Slf4j
@Api(value = "提供合作方相关的 Rest API", tags = {"合作方相关接口"})
public class PartnerController {
    @Autowired
    private PartnerService partnerService;

    @Autowired
    private BankService bankService;

    @Autowired
    private PaymentAddressService paymentAddressService;

    @ApiOperation("分页获取全部合作方信息")
    @PreAuthorize("hasAnyAuthority('PERMISSION_getAllPartnerByPageVO')")
    @PostMapping("getAllPartnerByPageVO")
    public ResponseEntity getAllPartnerByPageVO(@RequestBody PageVO pageVO){
        try {
            Page<TPrmPartner> tPrmPartners = partnerService.queryAllByPage(pageVO);
            return ResponseEntity.ok(tPrmPartners);
        }catch (Exception e){
            log.error("getAllPartnerByPageVO fail, error message:{}", e.getMessage());
        }
        return ResponseUtil.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ApiOperation("绑定合作方企业")
    @PostMapping("bindPartnerWithUser")
    public ResponseEntity bindPartnerWithUser(@RequestBody TPrmPartner tPrmPartner){
        try {
            TPrmPartner potentialPartner = partnerService.bindWithUser(tPrmPartner);
            return ResponseEntity.ok(potentialPartner);
        }catch (Exception e){
            log.error("bindPartnerWithUser fail, error message:{}", e.getMessage());
        }
        return ResponseUtil.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ApiOperation("申请成为正式合作方")
    @PostMapping("applyToFormalPartner")
    public ResponseEntity applyToFormalPartner(@RequestBody TPrmPartner tPrmPartner){
        try {
            TPrmPartner applier = partnerService.applyToFormalPartner(tPrmPartner);
            return ResponseEntity.ok(applier);
        }catch (Exception e){
            log.error("applyToFormalPartner fail, error message:{}", e.getMessage());
        }
        return ResponseUtil.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ApiOperation("删除操作")
    @PreAuthorize("hasAnyAuthority('PERMISSION_delete')")
    @DeleteMapping("delete")
    public ResponseEntity delete(@RequestParam Integer id){
        try {
            partnerService.deleteById(id);
            return ResponseEntity.ok("delete success");
        }catch (Exception e){
            log.error("delete error : {}", e.getMessage());
        }
        return ResponseUtil.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}