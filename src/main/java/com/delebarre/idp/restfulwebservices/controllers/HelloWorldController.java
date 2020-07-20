package com.delebarre.idp.restfulwebservices.controllers;

import helloworld.HelloWorldBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
public class HelloWorldController {
    @Autowired
    private MessageSource messageSource;

    @GetMapping(path="/helloworld")
    public String HelloWorld() {
        return "Hello World";
    }

    @GetMapping(path="/helloworldbean/{name}")
    public HelloWorldBean HelloWorldPathVariable(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello World %s",name));
    }

    @GetMapping(path="/helloworld-int")
    public String HelloWorldInt() {
        return messageSource.getMessage("good.morning.message",null, LocaleContextHolder.getLocale());
    }

}
