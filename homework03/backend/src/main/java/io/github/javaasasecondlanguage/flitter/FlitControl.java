package io.github.javaasasecondlanguage.flitter;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/flit")
public class FlitControl {

    @RequestMapping(
            path = "/add",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> addFlit(@RequestBody Map<String, String> info) {
        //System.out.println(info);
        var res = Context.addNewFLit(info.get("userToken"), info.get("content"));
        if (res.errorMessage == null)
            return ResponseEntity.ok(res);
        else
            return ResponseEntity.badRequest().body(res);
    }

    @RequestMapping(
            path = "/discover",
            method = RequestMethod.GET)
    ResponseEntity<?> discoverTenFlits() {
        return ResponseEntity.ok(Context.lastTenFlits());
    }

    @RequestMapping(
            value = "/list/{userName}",
            method = RequestMethod.GET)
    ResponseEntity<?> thisPersonFlits(@PathVariable("userName") String userName) {
        //System.out.println(userName);
        var res = Context.getAllFlitsFromThisPerson(userName);
        if (res.errorMessage == null)
            return ResponseEntity.ok(res);
        else
            return ResponseEntity.badRequest().body(res);
    }

    @RequestMapping(
            value = "/list/feed/{userToken}",
            method = RequestMethod.GET)
    ResponseEntity<?> getFeed(@PathVariable("userToken") String userToken) {
        //System.out.println(userName);
        var res = Context.getFeed(userToken);
        if (res.errorMessage == null)
            return ResponseEntity.ok(res);
        else
            return ResponseEntity.badRequest().body(res);
    }
}
