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
import top.wenxyn.partner.manager.entity.dao.prm.TPrmContract;
import top.wenxyn.partner.manager.entity.dao.prm.TPrmPartner;
import top.wenxyn.partner.manager.entity.vo.PageVO;
import top.wenxyn.partner.manager.service.prm.ContractService;
import top.wenxyn.partner.manager.util.ResponseUtil;

/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/3/17 1:02
 */
@RestController
@RequestMapping("contract")
@Slf4j
@Api(value = "提供合同相关的 Rest API", tags = {"合同相关接口"})
public class ContractController {
    @Autowired
    private ContractService contractService;

    @ApiOperation("分页获取全部合同信息")
    @PreAuthorize("hasAnyAuthority('PERMISSION_getAllContractByPageVO')")
    @PostMapping("getAllContractByPageVO")
    public ResponseEntity getAllContractByPageVO(@RequestBody PageVO pageVO){
        try {
            Page<TPrmContract> tPrmContracts = contractService.queryAllByPage(pageVO);
            return ResponseEntity.ok(tPrmContracts);
        }catch (Exception e){
            log.error("getAllContractByPageVO fail, error message:{}", e.getMessage());
        }
        return ResponseUtil.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ApiOperation("创建一个新合同")
    @PreAuthorize("hasAnyAuthority('PERMISSION_createContract')")
    @PostMapping("createContract")
    public ResponseEntity createContract(@RequestBody TPrmContract tPrmContract,
                                         @RequestBody Integer partnerId){
        try {
            contractService.createContract(tPrmContract, partnerId);
            return ResponseEntity.ok(null);
        }catch (Exception e){
            log.error("createContract fail, error message:{}", e.getMessage());
        }
        return ResponseUtil.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ApiOperation("删除操作")
    @PreAuthorize("hasAnyAuthority('PERMISSION_delete')")
    @DeleteMapping("delete")
    public ResponseEntity delete(@RequestParam Integer id){
        try {
            contractService.deleteById(id);
            return ResponseEntity.ok("delete success");
        }catch (Exception e){
            log.error("delete error : {}", e.getMessage());
        }
        return ResponseUtil.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
