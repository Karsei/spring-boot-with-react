package kr.pe.karsei.springbootwithreact.controllers;

import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api", produces = MediaTypes.HAL_JSON_VALUE)
public class HelloController {
    @GetMapping(value = "/hello")
    public String hello() {
        return "hello, world!";
    }
}
