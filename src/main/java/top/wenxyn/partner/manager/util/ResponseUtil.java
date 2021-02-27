package top.wenxyn.partner.manager.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/2/27 19:48
 */
public class ResponseUtil {

    public static ResponseEntity errorResponse(HttpStatus status){
        return ResponseEntity.status(status).body(status.getReasonPhrase());
    }
}
