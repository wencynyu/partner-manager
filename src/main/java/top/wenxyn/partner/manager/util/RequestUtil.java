package top.wenxyn.partner.manager.util;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.http.HttpRequest;

/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/3/5 1:38
 */
public final class RequestUtil {

    public static String getRequestBody(HttpServletRequest request) throws IOException {
        BufferedReader br = request.getReader();

        String str;
        StringBuilder stringBuilder = new StringBuilder();
        while((str = br.readLine()) != null){
            stringBuilder.append(str);
        }

        return stringBuilder.toString();
    }
}
