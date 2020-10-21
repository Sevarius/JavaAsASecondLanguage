package io.github.javaasasecondlanguage.flitter;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Clear {

    @RequestMapping(
            path = "/clear",
            method = RequestMethod.DELETE
    )
    ResponseEntity<?> clear() {
        Context.clear();
        return ResponseEntity.ok(179);
    }
}
