package com.fourthwall.smallcinema;

import io.swagger.annotations.ApiOperation;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShutdownController {

    private final ApplicationContext appContext;

    public ShutdownController(ApplicationContext appContext) {
        this.appContext = appContext;
    }

    @ApiOperation(value = "Shutdowns the application. Requires login and admin role.")
    @GetMapping(value = "/shutdown")
    public void shutdown() {
        SpringApplication.exit(appContext, () -> 0);
    }
}
