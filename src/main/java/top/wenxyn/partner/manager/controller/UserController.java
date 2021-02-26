package top.wenxyn.partner.manager.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.wenxyn.partner.manager.entity.TAuthUser;
import top.wenxyn.partner.manager.entity.vo.PageVO;
import top.wenxyn.partner.manager.service.UserService;

@RestController
@RequestMapping("user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("getAllUserByPage")
    public ResponseEntity getAllUserByPage(@RequestParam("page") Integer page){
        PageVO pageVO = new PageVO();
        pageVO.setPage(page);
        try {
            Page<TAuthUser> tAuthUsers = userService.queryAllByPage(pageVO);
            return ResponseEntity.ok(tAuthUsers);
        }catch (Exception e){
            log.error("getAllUserByPageVO fail, error message:{}", e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @GetMapping("getAllUserByPageVO")
    public ResponseEntity getAllUserByPageVO(@RequestBody PageVO pageVO){
        try {
            Page<TAuthUser> tAuthUsers = userService.queryAllByPage(pageVO);
            return ResponseEntity.ok(tAuthUsers);
        }catch (Exception e){
            log.error("getAllUserByPageVO fail, error message:{}", e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @PostMapping("addUser")
    public ResponseEntity addUser(@RequestBody TAuthUser user){
        try {
            TAuthUser insert = userService.insert(user);
            return ResponseEntity.ok(insert);
        }catch (Exception e){
            log.error("user insert fail, error message:{}", e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

}
