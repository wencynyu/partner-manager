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
import top.wenxyn.partner.manager.entity.dao.prm.TPrmCheckRecord;
import top.wenxyn.partner.manager.entity.vo.CheckContractVO;
import top.wenxyn.partner.manager.entity.vo.CheckPartnerVO;
import top.wenxyn.partner.manager.entity.vo.PageVO;
import top.wenxyn.partner.manager.service.prm.CheckRecordService;
import top.wenxyn.partner.manager.util.ResponseUtil;

/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/3/17 0:20
 */
@RestController
@RequestMapping("check")
@Slf4j
@Api(value = "提供审核功能相关的 Rest API", tags = {"审核功能相关接口"})
public class CheckRecordController {
    @Autowired
    private CheckRecordService checkRecordService;

    @ApiOperation("分页获取全部审核信息")
    @PreAuthorize("hasAnyAuthority('PERMISSION_getAllCheckRecordByPageVO') || hasAnyRole('ROLE_admin')")
    @PostMapping("getAllCheckRecordByPageVO")
    public ResponseEntity getAllCheckRecordByPageVO(@RequestBody PageVO pageVO){
        try {
            Page<TPrmCheckRecord> tPrmCheckRecords = checkRecordService.queryAllByPage(pageVO);
            return ResponseEntity.ok(tPrmCheckRecords);
        }catch (Exception e){
            log.error("getAllCheckRecordByPageVO fail, error message:{}", e.getMessage());
        }
        return ResponseUtil.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ApiOperation("审核合作方接口")
    @PreAuthorize("hasAnyAuthority('PERMISSION_checkPartner') || hasAnyRole('ROLE_admin')")
    @PostMapping("checkPartner")
    public ResponseEntity checkPartner(@RequestBody CheckPartnerVO checkPartnerVO){
        try {
            TPrmCheckRecord checkRecord = checkRecordService.checkPartner(checkPartnerVO.getTPrmCheckRecord(),
                    checkPartnerVO.getTPrmPartner());
            return ResponseEntity.ok(checkRecord);
        }catch (Exception e){
            log.error("checkPartner fail, error message:{}", e.getMessage());
        }
        return ResponseUtil.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ApiOperation("审核合同接口")
    @PreAuthorize("hasAnyAuthority('PERMISSION_checkContract') || hasAnyRole('ROLE_admin')")
    @PostMapping("checkContract")
    public ResponseEntity checkContract(@RequestBody CheckContractVO checkContractVO){
        try {
            TPrmCheckRecord checkRecord = checkRecordService.checkContract(checkContractVO.getTPrmCheckRecord(),
                    checkContractVO.getTPrmContract());
            return ResponseEntity.ok(checkRecord);
        }catch (Exception e){
            log.error("checkContract fail, error message:{}", e.getMessage());
        }
        return ResponseUtil.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ApiOperation("删除操作")
    @PreAuthorize("hasAnyAuthority('PERMISSION_delete') || hasAnyRole('ROLE_admin')")
    @DeleteMapping("delete")
    public ResponseEntity delete(@RequestParam Integer id){
        try {
            checkRecordService.deleteById(id);
            return ResponseEntity.ok("delete success");
        }catch (Exception e){
            log.error("delete error : {}", e.getMessage());
        }
        return ResponseUtil.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
