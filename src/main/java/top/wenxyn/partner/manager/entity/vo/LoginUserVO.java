package top.wenxyn.partner.manager.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/3/6 21:41
 */
@Data
@ApiModel(value = "登录角色实体",
        description = "用在登录鉴权过程，前端只需要传入以下参数即可")
public class LoginUserVO {
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "验证码")
    private String verifyCode;
}
