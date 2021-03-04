package top.wenxyn.partner.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.junit.Test;
import top.wenxyn.partner.manager.entity.TAuthRole;
import top.wenxyn.partner.manager.entity.TAuthUser;
import top.wenxyn.partner.manager.util.SerializeUtil;

import java.util.HashSet;
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
}