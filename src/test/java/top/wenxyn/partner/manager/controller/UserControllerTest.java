package top.wenxyn.partner.manager.controller;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import top.wenxyn.partner.manager.service.UserService;

public class UserControllerTest {
    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Before
    public void setUp(){

    }
}
