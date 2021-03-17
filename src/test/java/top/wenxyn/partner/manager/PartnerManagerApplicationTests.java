package top.wenxyn.partner.manager;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.wenxyn.partner.manager.component.EmailService;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class PartnerManagerApplicationTests {

    @Autowired
    private EmailService emailService;

    @Test
    void contextLoads() {
    }

    @Test
    public void sendEmail(){
        emailService.sendMail("1351819147@qq.com", "a email", "hello world!!");
    }

}
