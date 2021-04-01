package top.wenxyn.partner.manager.component;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import top.wenxyn.partner.manager.repository.auth.TRoleRepository;
import top.wenxyn.partner.manager.repository.auth.TUserRepository;
import top.wenxyn.partner.manager.repository.auth.TUserRoleRepository;
import top.wenxyn.partner.manager.entity.dao.auth.TAuthRole;
import top.wenxyn.partner.manager.entity.dao.auth.TAuthUser;
import top.wenxyn.partner.manager.entity.dao.auth.TAuthUserRole;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/3/14 23:05
 */
@Component
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private TRoleRepository tRoleRepository;
    @Autowired
    private TUserRoleRepository tUserRoleRepository;
    @Autowired
    private TUserRepository tUserRepository;

    private static final String RECEIVER_ROLE_NAME = "admin";

    public String[] getReceivers() {
        TAuthRole byName = tRoleRepository.findByName(RECEIVER_ROLE_NAME);
        List<Integer> userIds = tUserRoleRepository.findByRoleId(byName.getId()).stream()
                .map(TAuthUserRole::getUserId)
                .collect(Collectors.toList());
        List<String> emailList = tUserRepository.findAllById(userIds).stream()
                .map(TAuthUser::getEmail)
                .filter(StringUtils::isNotEmpty)
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(emailList)){
            return new String[]{"1351819147@qq.com"};
        }
        String[] emails = new String[emailList.size()];
        for (int i = 0; i < emails.length; i++) {
            emails[i] = emailList.get(i);
        }
        return emails;
    }

    public void sendMail(String subject, String text, String to) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }

    public void sendMail(String subject, String text, String... to) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }
}
