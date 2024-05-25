package br.com.alura.med.voll.alura_medVoll_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class Main {

    @GetMapping
    public String hello(){
        return "hello World";
    }

}
