package com.fellowchicken.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Api(tags = {"swagger"})
@RequestMapping(name = "Swagger UI", path = "/swagger")
public class SwaggerController {
    @GetMapping
    public String index() {
        return "redirect:/swagger-ui.html";
    }
}
