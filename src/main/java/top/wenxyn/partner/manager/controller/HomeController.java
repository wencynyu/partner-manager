package top.wenxyn.partner.manager.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import top.wenxyn.partner.manager.common.Constant;
import top.wenxyn.partner.manager.entity.dao.auth.TAuthUser;
import top.wenxyn.partner.manager.exception.RegisterException;
import top.wenxyn.partner.manager.service.auth.UserService;
import top.wenxyn.partner.manager.util.ResponseUtil;
import top.wenxyn.partner.manager.util.VerifyCodeUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/3/5 0:19
 */
@RestController
@Slf4j
@Api(value = "提供主页相关的 Rest API", tags = {"主页相关接口"})
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @ApiOperation("用户注册接口")
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody TAuthUser user){
        if (StringUtils.isEmpty(user.getUsername()) ||  StringUtils.isEmpty(user.getPassword())){
            return ResponseUtil.errorResponse(HttpStatus.BAD_REQUEST);
        }
        try {
            userService.register(user);
            return ResponseEntity.ok(user);
        }catch (RegisterException e){
            log.info("username [{}] has been used.", user.getUsername());
        }
        return ResponseUtil.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ApiOperation("主页接口")
    @GetMapping("/index")
    public ResponseEntity index(){
        return ResponseEntity.ok("hello world!!");
    }

    @ApiOperation("鉴权主页接口")
    @PreAuthorize("hasAnyAuthority('PERMISSION_authIndex')")
    @GetMapping("/authIndex")
    public ResponseEntity authIndex(){
        return ResponseEntity.ok("hello auth world!!");
    }

    @ApiOperation("验证码获取接口，主要用于登录使用")
    @GetMapping("/getVerifyCode")
    public void getVerifyCode(HttpServletRequest request, HttpServletResponse response){
        VerifyCodeUtil verifyCode = new VerifyCodeUtil();
        request.getSession().setAttribute(Constant.VERIFY_CODE_SESSION_NAME, verifyCode.getText());
        try {
            ResponseUtil.printImage(response, verifyCode.getImage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ApiOperation("在线人数统计接口")
    @GetMapping("/getActiveUserCount")
    public ResponseEntity getActiveUserCount(){
        return ResponseEntity.ok(Optional
                .ofNullable(stringRedisTemplate.opsForValue().get(Constant.ACTIVE_USER_COUNT))
                .orElse("0"));
    }
}
