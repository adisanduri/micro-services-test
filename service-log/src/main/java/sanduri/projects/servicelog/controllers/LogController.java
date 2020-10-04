package sanduri.projects.servicelog.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {

    @PostMapping("/logList")
    public void log(@RequestParam Object object) {

        System.out.println(object.toString());
    }

    @GetMapping("/")
    public String index() {
        return "Service Log is UP!";
    }

}