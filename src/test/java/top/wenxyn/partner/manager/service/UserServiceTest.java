package top.wenxyn.partner.manager.service;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import top.wenxyn.partner.manager.dao.TUserRepository;

public class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private TUserRepository tUserRepository;

    @Before
    public void setUp(){

    }
}
