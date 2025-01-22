package com.Foro.Alura.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public class SaludoController {

    @RestController
    @RequestMapping("/hello")
    public class HelloController {

        @GetMapping
        public String helloWorld() {
            return "Hello world from Europe!";
        }

    }

}
