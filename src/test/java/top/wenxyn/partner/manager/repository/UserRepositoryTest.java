package top.wenxyn.partner.manager.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.wenxyn.partner.manager.dao.TUserRepository;
import top.wenxyn.partner.manager.entity.TAuthUser;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserRepositoryTest {

    @Autowired
    private TUserRepository tUserRepository;

    @Test
    public void insertTest(){
        TAuthUser tAuthUser = new TAuthUser();
        tAuthUser.setName("yuwenxin");
        tUserRepository.save(tAuthUser);
    }
}
