package org.xlys.ja.web;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/ping")
    public String ping() {
        return "Pong";
    }

    /**
     * demonstrate the functionality supported by JavaAgent
     * */
    @SneakyThrows
    @GetMapping("/cost/{sleepDuration}")
    public String costCount(@PathVariable long sleepDuration) {
        Thread.sleep(sleepDuration);
        log.info("Method:[TestController#costCount]'s invocation gets triggered");
        return "Success";
    }
}
