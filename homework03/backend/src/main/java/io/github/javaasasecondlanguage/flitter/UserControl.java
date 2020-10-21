package io.github.javaasasecondlanguage.flitter;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
//@RequestMapping("/user")
public class UserControl {

    @RequestMapping(
            path = "/user/register",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> registerUser(
            @RequestBody Map<String, String> userName) {
        var name = userName.get("userName");
        //System.out.println(name);
        var res = Context.registerPerson(name);
        if (res.errorMessage == null)
            return ResponseEntity.ok(res);
        else
            return ResponseEntity.badRequest().body(res);
    }

    @RequestMapping(
            path = "/user/list",
            method = RequestMethod.GET
    )
    ResponseEntity<?> list() {
        return ResponseEntity.ok(Context.usersList());
    }

}
