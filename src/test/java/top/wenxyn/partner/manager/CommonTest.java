package top.wenxyn.partner.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.mail.util.MailSSLSocketFactory;
import org.junit.Test;
import top.wenxyn.partner.manager.entity.dao.auth.TAuthRole;
import top.wenxyn.partner.manager.entity.dao.auth.TAuthUser;
import top.wenxyn.partner.manager.util.SerializeUtil;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class CommonTest {

    @Test
    public void transientTest() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        TAuthUser user = new TAuthUser();
        user.setName("yuwenxin");
        Set<TAuthRole> roles = new HashSet<>();
        roles.add(new TAuthRole());
        user.setRoles(roles);
        System.out.println(user);
        String userJson = mapper.writeValueAsString(user);
        System.out.println(userJson);
    }

    @Test
    public void serializationTest(){
        TAuthUser user = new TAuthUser();
        user.setPassword("123");
        String userJson = SerializeUtil.obj2String(user);
        TAuthRole tAuthRole = SerializeUtil.string2Obj(userJson, TAuthRole.class);
        System.out.println(tAuthRole);
    }

    @Test
    public void emailTest(){
        String from = "1351819147@qq.com";
        String to = "1351819147@qq.com";

        try {
            Properties properties = new Properties();
            //发送服务器需要身份验证
            properties.setProperty("mail.smtp.auth", "true");
            //发送邮件服务器的主机名
            properties.setProperty("mail.smtp.host", "smtp.qq.com");
            //端口号
            properties.setProperty("mail.smtp.port", "465");
            //发送邮件协议
            properties.setProperty("mail.transport.protocol", "smtp");
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.ssl.socketFactory", sf);
            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("1351819147@qq.com", "qytuzoenxtxzidie");
            }
            });

            MimeMessage message = new MimeMessage(session);
            message.setFrom(from);
            message.addRecipients(MimeMessage.RecipientType.TO, to);
            message.setSubject("subject");
            message.setText("hello email!!");
            Transport.send(message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}