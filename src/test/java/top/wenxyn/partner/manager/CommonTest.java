package top.wenxyn.partner.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.junit.Test;
import top.wenxyn.partner.manager.entity.TAuthUser;

import java.util.HashSet;
import java.util.Set;

public class CommonTest {

    @Test
    public void transientTest() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        TAuthUser user = new TAuthUser();
        user.setName("yuwenxin");
        Set<String> roles = new HashSet<>();
        roles.add("admin");
        user.setRoles(roles);
        System.out.println(user);
        String userJson = mapper.writeValueAsString(user);
        System.out.println(userJson);
    }
}