package io.github.javaasasecondlanguage.flitter;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class SubscriptionControl {

    @RequestMapping(
            path = "/subscribe",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> subscribe(
            @RequestBody Map<String, String> info) {
        var leftToken = info.get("subscriberToken");
        var rightUserName = info.get("publisherName");
        //System.out.println(name);
        var res = Context.subscribe(leftToken, rightUserName);
        if (res.errorMessage == null)
            return ResponseEntity.ok(res);
        else
            return ResponseEntity.badRequest().body(res);
    }

    @RequestMapping(
            path = "/unsubscribe",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> unsubscribe(
            @RequestBody Map<String, String> info) {
        var leftToken = info.get("subscriberToken");
        var rightUserName = info.get("publisherName");
        //System.out.println(name);
        var res = Context.unsubscribe(leftToken, rightUserName);
        if (res.errorMessage == null)
            return ResponseEntity.ok(res);
        else
            return ResponseEntity.badRequest().body(res);
    }

    @RequestMapping(
            path = "/subscribers/list/{userToken}",
            method = RequestMethod.GET)
    ResponseEntity<?> subscribers(@PathVariable("userToken") String userToken) {
        var res = Context.getListSubscribers(userToken);
        if (res.errorMessage == null)
            return ResponseEntity.ok(res);
        else
            return ResponseEntity.badRequest().body(res);
    }

    @RequestMapping(
            path = "/publishers/list/{userToken}",
            method = RequestMethod.GET)
    ResponseEntity<?> publishers(@PathVariable("userToken") String userToken) {
        var res = Context.getListSubscriptions(userToken);
        if (res.errorMessage == null)
            return ResponseEntity.ok(res);
        else
            return ResponseEntity.badRequest().body(res);
    }

}
