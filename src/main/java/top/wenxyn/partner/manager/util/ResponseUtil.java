package top.wenxyn.partner.manager.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/2/27 19:48
 */
public final class ResponseUtil {

    public static ResponseEntity errorResponse(HttpStatus status){
        return ResponseEntity.status(status).body(status.getReasonPhrase());
    }

    public static void printImage(HttpServletResponse response, BufferedImage image) throws IOException {
        response.setContentType("image/jpeg");
        response.setHeader("Cache-Control", "no-cache");

        VerifyCodeUtil.printImage(image, response.getOutputStream());
    }
}
